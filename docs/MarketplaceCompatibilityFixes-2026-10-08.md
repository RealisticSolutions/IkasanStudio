# Marketplace compatibility fixes — 8 October 2026

The beta.1 Marketplace report identified two internal API usages (one class and one
method), one method scheduled for removal, and an unresolved optional dependency
on the containing community plugin. This work prepares a separate **1.0.0-beta.2**
candidate. The previously submitted beta.1 ZIPs and checksums are preserved.

## Shared changes for upstream

`UserImplementedClassRelocator` now uses `JavaRefactoringFactory` and
`MoveClassesOrPackagesRefactoring` instead of the internal
`MoveClassesOrPackagesUtil.doMoveClass`. The refactoring owns its write command and
updates Java usages. A smart pointer reacquires the moved class before optional
rename and Spring bean-name updates. An imported Java source root is required.
Tests exercise handwritten content, Java imports, collision naming, generated bean
names, custom bean names and components which do not require a generated stub.

`MigrationController` delegates Maven synchronization to the project-level
`MigrationMavenSync` service. Its injected coroutine scope follows project/plugin
lifetime. It awaits `updateAllMavenProjects(MavenSyncSpec.full(...))` instead of
`forceUpdateProjects(...)` followed by `scheduleImportAndResolve()`.
Compilation is requested only after the suspend function finishes, a workspace
import has committed, and Maven reports no project problems. POM/import failures
retain the migration snapshot and show the existing failure notification; scope
cancellation or project closure prevents compilation. The compiler's existing
smart-mode and model-identity guards remain in place.

The root Gradle module now compiles Kotlin using the IDE-provided runtime. The
newer-IDE focused test task is `testIdeCompatibility`; its commercial startup
workaround is confined to the test sandbox because the headless test runner
flattens plugin class loaders. Normal IDE sessions keep their plugin configuration.

## Optional MCP dependency

The native content module deliberately depends on its containing plugin to reach
`StudioAiService`. Keep that dependency. The
[JetBrains modular-plugin documentation](https://plugins.jetbrains.com/docs/intellij/modular-plugins.html#class-loaders)
explains that declared dependencies supply parent class loaders.

Changing it to a module alias was tested and rejected: IDEA 2026.2.2 did not resolve
the alias and disabled the native module. The shipped descriptor therefore retains
the community plugin ID. Upstream must retain its own plugin ID instead.

The native extension loaded in both the focused IDE tests and separate headless
IDE processes using production class loaders on IDEA 2026.2.2 and 263.6259.32.
The separate runtime probe verified that the optional and main modules use
different class loaders and resolve the **same** `StudioAiService` class.
Probe results are under `build/reports/compatibility-smoke/`.

This evidence points to a Marketplace dependency-resolution warning, rather than
an unavailable runtime dependency. Local Plugin Verifier succeeds. Do not remove
the dependency merely to silence the Marketplace report; if it persists on the new
upload, send JetBrains the descriptor and these results.

## Verification

Results: **1,371 root tests passed**, **16 focused tests passed on each of IDEA
2026.2.2 and 263.6259.32**, headless module-loading probes passed on both newer
IDEs, archive audit passed, and all four IDE boundaries verified as compatible.

Commands from the project root:

```bash
./gradlew check buildPlugin verifyReleaseArchive verifyPlugin testIdeCompatibility
./gradlew verifyPlugin -PverificationNewestIde=263.6259.32
./gradlew testIdeCompatibility -PcompatibilityTestIdeVersion=263.6259.32
```

Binary verification uses Plugin Verifier 1.410. Reports live under
`build/reports/pluginVerifier/`, and archive evidence under
`build/reports/release/archive.json`.

The remaining deprecated and experimental APIs have not been suppressed. In
particular, Maven's replacement asynchronous APIs are public but experimental.
IDEA 2026.2.2 reports 20 deprecated and 13 experimental usages; 263.6259.32 reports
21 deprecated and 13 experimental usages. Neither reports an internal API usage,
scheduled-for-removal usage or binary compatibility problem. IDEA 2024.2 and
2024.3.7 report one deprecated and 13 experimental usages each.

These checks cover binary compatibility and focused behavior. They do not replace
the interactive install, upgrade, new-project, migration and application acceptance
checks in [release-candidate verification](ReleaseCandidateVerification.md).

## Community release changes

The community-only commit changes the version to `1.0.0-beta.2`, adds concise
version-specific Marketplace change notes, and makes the Marketplace channel
explicitly `default`. A beta version suffix no longer silently selects a custom
channel. No upload or publication is performed by these changes.

The new candidate is `build/distributions/ikasanstudio-1.0.0-beta.2.zip`.
Its unsigned SHA-256 is:

```text
0ca420dfcf5af47be41eeb5ef94cdb809ccf7a6ea0704edeadd1edeb957e8efc
```

Sign this new ZIP before uploading it to the existing hidden listing; the beta.1
signature cannot be reused for changed bytes. Using the signing files previously
created on this machine:

```bash
read -rsp 'Signing key password: ' IKASAN_SIGNING_PASSWORD
printf '\n'
export PRIVATE_KEY_PASSWORD="$IKASAN_SIGNING_PASSWORD"
export PRIVATE_KEY="$(cat "$HOME/.local/share/ikasan-studio-signing/private-encrypted.pem")"
export CERTIFICATE_CHAIN="$(cat "$HOME/.local/share/ikasan-studio-signing/chain.crt")"
./gradlew signPlugin verifyPluginSignature
unset PRIVATE_KEY CERTIFICATE_CHAIN PRIVATE_KEY_PASSWORD IKASAN_SIGNING_PASSWORD

(cd build/distributions &&
  sha256sum ikasanstudio-1.0.0-beta.2-signed.zip > ikasanstudio-1.0.0-beta.2-signed.zip.sha256)
```

Confirm signing and signature verification actually execute successfully. Upload
`ikasanstudio-1.0.0-beta.2-signed.zip`, choosing the default channel and retaining
the listing's hidden status while review/preparation continues. Signing credentials
are handled locally; do not paste them into chat or commit them.
