# Ikasan Studio changelog

User-facing release notes for Ikasan Studio. Publication remains subject to candidate verification and maintainer sign-off.

## [Unreleased]

## [1.0.0-beta.3] - 2026-11-18

### Added

- Retain the Realistic Solutions community plugin identity, support links and manual candidate release workflow.

- Bundled Ikasan 5.1.0 support targeting JDK 25, alongside Ikasan 3.3.9 (Java 11) and 4.1.6 (Java 17). V5 migration is not a supported route; use the documented V3/V4 migration workflow.
- A guided running-module import workflow with REST diagnostics, source-checkout identity checks, reactor-module selection, native source comparisons and configuration import.
- Reviewed Spring registration for copied components, recovery of missing literal builder class settings and JMS trusted-package helpers, and **Copy all new** for missing destination files. Changes are staged, preserve existing files and reject stale reviews.
- Model repair previews with validation and exact backups for supported missing-pack and revision-lock problems.
- Per-component local harness overrides, developer-owned environment configuration and reusable environment Run/Debug configurations.

### Improved

- Runtime component/configuration mapping, including FTP/SFTP producers, JNDI maps and wrapper/delegate contracts. Imported filter delegates are no longer confused with their outer runtime components.
- Multi-project harness isolation and ownership reporting. Stop warns about endpoints used by other open projects; green indicates locally owned running services and amber indicates shared-only SMTP availability.
- Module H2 defaults now follow HTTP + 1 / + 2 when unset. Explicit values are preserved, per-port **Default** buttons recalculate settings, and port validation checks ranges and duplicate local service ports.
- Maven initialization recovery, generated-module discovery, JDK alignment after import and actionable module-startup diagnostics.
- More compact import reviews and dialogs, attention indicators for pending registration, and canvas spacing that keeps server nodes clear of flow controls.
- Generated business-test diagnostics, source navigation and preservation of developer-owned tests and implementation files.

### Compatibility and upgrade

- The released V5.1.0 adapter is revision **1.0.8**, generator API **4**. Existing locked V5 projects require explicit revision adoption; re-import models affected by earlier component/delegate classification issues.
- The unused V5.0.1-SNAPSHOT pack has been retired. Supported repair paths require review and preserve backups; no silent migration is performed.
- IDE compatibility and release readiness are recorded in the [candidate audit](docs/ReleaseAudit-2026-11-18.md). Installation, upgrade and platform workflow checks remain distinct from automated tests.

The [detailed development history](docs/DevelopmentLog-BeforeBeta3.md) retains intermediate fixes and revision changes.

## [1.0.0-beta.2]

- Community quick-fix release: replace removal-bound Maven synchronization and internal Java class-move APIs; retain the community Marketplace default channel.
