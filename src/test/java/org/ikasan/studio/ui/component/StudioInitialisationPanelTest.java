package org.ikasan.studio.ui.component;

import com.intellij.ui.components.labels.LinkLabel;
import org.ikasan.studio.ui.StudioBundle;
import org.junit.jupiter.api.Test;

import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StudioInitialisationPanelTest {
    @Test void repairIsDiscoverableOnFailureAndHiddenDuringLoading() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            var panel = new StudioInitialisationPanel(() -> { }, index -> { }, () -> { });
            var repair = findRepair(panel);
            assertThat(repair).isNotNull();
            assertThat(repair.isVisible()).isFalse();
            panel.showFailure("A pack revision is unavailable", List.of(1));
            assertThat(repair.isVisible()).isTrue();
            panel.showReadingProject();
            assertThat(repair.isVisible()).isFalse();
            panel.showFailure("The model needs review", List.of());
            assertThat(repair.isVisible()).isTrue();
            panel.showLoadingComponents();
            assertThat(repair.isVisible()).isFalse();
        });
    }

    private static LinkLabel<?> findRepair(Container parent) {
        for (Component child : parent.getComponents()) {
            if (child instanceof LinkLabel<?> link && StudioBundle.message("modelRepair.Link").equals(link.getText())) return link;
            if (child instanceof Container container) {
                var result = findRepair(container);
                if (result != null) return result;
            }
        }
        return null;
    }
}
