package org.ikasan.studio.intellij.project;

import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.testFramework.HeavyPlatformTestCase;
import com.intellij.testFramework.PlatformTestUtil;
import org.ikasan.studio.core.TestFixtures;
import org.ikasan.studio.core.generation.GenerationRequest;
import org.ikasan.studio.core.generator.ModelTemplate;
import org.ikasan.studio.intellij.migration.MigrationJdk;
import org.ikasan.studio.ui.StudioUIUtils;
import org.jetbrains.idea.maven.project.MavenProjectsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

public class ModelImporterJdkOrderingHeavyTest extends HeavyPlatformTestCase {
    public void testMavenRefreshFollowsCommittedGenerationAndJdkAlignment() throws Exception {
        String source = ModelTemplate.create(TestFixtures.getMyFirstModuleIkasanModule("V3.3.9", List.of()));
        var completion = new CompletableFuture<Void>();
        var order = new ArrayList<String>();
        var sdk = mock(Sdk.class);
        when(sdk.getName()).thenReturn("test-java-11");
        var table = mock(ProjectJdkTable.class);
        when(table.getAllJdks()).thenReturn(new Sdk[]{sdk});
        var manager = mock(MavenProjectsManager.class);
        doAnswer(invocation -> { order.add("maven"); return null; }).when(manager).forceUpdateAllProjectsOrFindAllAvailablePomFiles();
        try (var files = mockStatic(StudioProjectFiles.class);
             var jdks = mockStatic(ProjectJdkTable.class);
             var migration = mockStatic(MigrationJdk.class);
             var maven = mockStatic(MavenProjectsManager.class);
             var messages = mockStatic(StudioUIUtils.class)) {
            files.when(() -> StudioProjectFiles.refreshCodeFromModel(myProject, GenerationRequest.full(), false)).thenReturn(completion);
            jdks.when(ProjectJdkTable::getInstance).thenReturn(table);
            migration.when(() -> MigrationJdk.matches(sdk, 11)).thenReturn(true);
            migration.when(() -> MigrationJdk.apply(myProject, sdk, 11)).thenAnswer(invocation -> { order.add("jdk"); return null; });
            maven.when(() -> MavenProjectsManager.getInstance(myProject)).thenReturn(manager);
            ModelImporter.importFromText(myProject, source, "import ordering test");
            assertTrue("Do not configure or refresh before generation commits", order.isEmpty());
            files.verify(() -> StudioProjectFiles.refreshCodeFromModel(myProject, GenerationRequest.full(), false));
            completion.complete(null);
            PlatformTestUtil.waitWithEventsDispatching("Import completion never refreshed Maven", () -> order.size() == 2, 10);
            assertEquals(List.of("jdk", "maven"), order);
            messages.verify(() -> StudioUIUtils.displayIdeaInfoMessage(eq(myProject), anyString()));
        }
    }

    public void testFailedGenerationDoesNotReconfigureJdkOrRefreshMaven() throws Exception {
        String source = ModelTemplate.create(TestFixtures.getMyFirstModuleIkasanModule("V3.3.9", List.of()));
        var completion = new CompletableFuture<Void>();
        var manager = mock(MavenProjectsManager.class);
        var warned = new java.util.concurrent.atomic.AtomicBoolean();
        try (var files = mockStatic(StudioProjectFiles.class);
             var migration = mockStatic(MigrationJdk.class);
             var maven = mockStatic(MavenProjectsManager.class);
             var messages = mockStatic(StudioUIUtils.class)) {
            files.when(() -> StudioProjectFiles.refreshCodeFromModel(myProject, GenerationRequest.full(), false)).thenReturn(completion);
            maven.when(() -> MavenProjectsManager.getInstance(myProject)).thenReturn(manager);
            messages.when(() -> StudioUIUtils.displayIdeaWarnMessage(eq(myProject), anyString()))
                    .thenAnswer(invocation -> { warned.set(true); return null; });
            ModelImporter.importFromText(myProject, source, "failed import ordering test");
            completion.completeExceptionally(new IllegalStateException("injected generation failure"));
            PlatformTestUtil.waitWithEventsDispatching("Generation failure was not reported", warned::get, 10);
            migration.verifyNoInteractions();
            verifyNoInteractions(manager);
        }
    }
}
