# Ikasan Studio features

Design, build, run and test Ikasan integrations inside IntelliJ IDEA.

This matrix describes capabilities implemented in the current source checkout, reviewed
on **18 November 2026**. It includes bundled Ikasan 5.1.0 and the source-import workflow.
An older downloaded ZIP may contain a different feature set: check its release notes
and [supported versions](SupportedVersions.md). Source availability does not imply
Marketplace publication; see [installation](Installation.md) for distribution routes.

## Visual flow design

| Feature | Developer benefit |
| --- | --- |
| Drag-and-drop flow editor | Compose integrations visually inside IntelliJ. |
| Module, flow and component property editors | Configure behaviour without hand-writing repetitive wiring. |
| Routers, filters, splitters, brokers and translators | Build branching and transformation paths. |
| Exception resolver configuration | Define exception handling and recovery policies. |
| Property search and optional settings | Find configuration quickly. |
| Canvas-to-code navigation | Open the implementation behind a component. |
| Undo/redo and flow duplication | Revise designs and reuse existing flows. |

## Components and code generation

| Feature | Developer benefit |
| --- | --- |
| JMS, FTP, SFTP, local-file and email components | Connect common integration transports. |
| Scheduled and event-generating consumers | Start processing from timers or generated events. |
| Custom component and implementation scaffolds | Add application-specific Java behaviour. |
| Java, Maven and properties generation | Generate application structure from the visual model. |
| Separate generated and developer-owned source | Regenerate wiring while retaining business implementations. |
| Payload-type guidance | Spot declared type mismatches before running. |
| Suggested converter recipes | Generate common payload extraction and construction code. |
| JMS MapMessage-to-payload and payload-to-map converters | Bridge JMS map messages and file-transfer payloads. |

## Import and reuse

| Feature | Developer benefit |
| --- | --- |
| Studio model import and export | Transfer saved designs between projects. |
| Runtime metadata and configuration JSON import | Reconstruct existing Ikasan modules from exported JSON. |
| Flow copy/paste between matching-version projects | Reuse flows and their flow-owned Java implementations. |
| Find and replace model references | Update shared class and bean references after refactoring. |
| Generate Studio module from running Ikasan module | Acquire module metadata and component configuration through REST. |
| Import Ikasan module using import Wizard | Bring topology, implementations and properties into one workflow. |
| Import Ikasan source code using import Wizard | Assisted merge of bespoke classes, unit tests, helpers and additional dependencies. |
| Import Ikasan configuration using import Wizard | Assisted merge of configurations and properties. |
| Checkout identity and reactor-module checks | Avoid importing code from the wrong application. |
| Whole-file and partial source copying | Review native diffs, save previews and revert pending changes. |
| Support-file customisation import | Retain explicitly copied debug/support changes during regeneration. |
| Inline wiring and scan findings | Resolve issues beside the affected source or dependency. |
| Copy all new | Stage missing source files together while preserving existing files. |
| Reviewed Spring registration and builder/delegate recovery | Restore supported wiring with staged, reviewable changes. |

## Environment configuration

| Feature | Developer benefit |
| --- | --- |
| Generated Spring properties and external overrides | Separate configured defaults from deployment settings. |
| Properties import from files or pasted text | Recover configuration used by bespoke classes. |
| Shared and named environment files | Maintain settings for dev, int and other targets. |
| Key-change review and native value diffs | Inspect configuration before replacing files. |
| Git-ignored local configuration | Keep machine-specific settings outside shared configuration. |
| Environment Run/Debug configurations | Switch environments without changing canvas defaults. |

## Run, debug and inspect

| Feature | Developer benefit |
| --- | --- |
| IntelliJ Application Run/Debug configurations | Run modules with normal IDE process and debugger controls. |
| Canvas flow start/stop controls | Control individual flows during development. |
| Debug components and code navigation | Place breakpoints along the processing path. |
| Wiretaps before and after components | Capture events around selected processing steps. |
| Module-local Blue Console access | Open flow controls, REST APIs and module administration. |
| Wiretap and excluded-event viewers | Inspect retained runtime records without the Dashboard. |
| FTP/SFTP duplicate-history viewer | Investigate persisted duplicate-filter records. |
| SFTP file browser | Browse, download and explicitly delete selected remote files. |
| Independent H2 launch | Start a separate development database server. |
| H2 launch through IntelliJ's Run window | Use project-resolved H2, process tracking and normal Stop/Rerun controls. |
| HTTP-derived H2 defaults | Default unset H2 TCP and web ports to HTTP + 1 and HTTP + 2 while preserving explicit values. |

## Local development services

| Feature | Developer benefit |
| --- | --- |
| Local FTP test server | Exercise file transfers with inspectable test directories. |
| Local SFTP test server | Test SSH file transfers without an installed SSH service. |
| Local mail capture and web inbox | Inspect outgoing email without sending it externally. |
| JMS test-reader flows | Inspect producer messages using an existing broker. |
| Test-message injection | Send selected payloads into supported running consumers. |
| Real file-scan triggers | Exercise configured acquisition rules on demand. |
| Per-component harness overrides | Select local FTP, SFTP and email settings for the next Run/Debug launch. |
| Harness ownership status and stop warnings | Distinguish project-owned services from shared SMTP and warn about other projects using an endpoint. |

## Testing and validation

| Feature | Developer benefit |
| --- | --- |
| Generated structural verification tests | Check flow factories and declared implementation contracts. |
| Editable JUnit business flow-test scaffolds | Supply meaningful inputs, paths and expected outputs. |
| Single-flow and bulk test generation | Create tests for selected flows together. |
| Local FTP, SFTP and SMTP test fixtures | Exercise supported transports in isolated test environments. |
| Batch, splitter, filter and router test support | Express delivery counts and expected processing paths. |
| First delivery, idle readiness and later delivery checks | Test long-lived flow behaviour. |
| Native Expected/Actual failure diagnostics | Compare results and navigate to developer assertions. |
| Validate Module and implementation-readiness findings | Find invalid properties, missing source and recognised unfinished scaffolds. |
| JMS MapMessage test fixtures | Supply map messages to generated JMS flow tests. |

## Versions, migration and extensibility

| Feature | Developer benefit |
| --- | --- |
| Ikasan 3.3.9 pack — Java 11 | Generate applications for the supported Ikasan 3 baseline. |
| Ikasan 4.1.6 pack — Java 17 | Generate applications for the supported Ikasan 4 baseline. |
| Bundled Ikasan 5.1.0 pack — Java 25 | Build Ikasan 5 applications without snapshot opt-in. |
| Previewed Ikasan 3.3.9 ↔ 4.1.6 migration | Review model, generated-code and Maven changes before applying. |
| Supported import replacements and JDK alignment | Reduce repetitive work during version migration. |
| Migration snapshots and restoration | Recover files changed by a migration. |
| External meta-pack installation and management | Add independently packaged component libraries and templates. |
| Project pack-revision locks | Prevent silent adoption of a different installed pack revision. |
| Exportable command-line tools | Validate, render and perform supported migrations outside IntelliJ. |

## Optional AI assistance and recovery

| Feature | Developer benefit |
| --- | --- |
| MCP connection to compatible assistants | Expose the live model and selected component catalogue. |
| Validated model-change proposals | Add or edit supported flows, components, routing and properties. |
| Proposal review, approval settings and undo | Control how assistant changes reach the design. |
| File-based AI proposals | Exchange structured changes without a live MCP connection. |
| Generated schemas, catalogues and project guidance | Give coding assistants project-specific context. |
| Automatic model backups and recovery mode | Recover from unreadable or invalid saved models. |
| Model repair previews | Review supported pack/revision repairs and keep exact backups. |
| Diagnostic bundle collection | Share structured troubleshooting evidence. |
| Detailed runtime-import diagnostics | Investigate REST, version-matching and configuration-import failures. |

## Scope

Component and tool availability depends on the selected pack. Business implementations and test expectations remain developer-owned. Wiretap and excluded-event viewers are read-only; SFTP deletion requires an explicit action, and JMS readers require a broker. Supported version migration remains **3.3.9 ↔ 4.1.6**; bundling Ikasan 5 does not add a supported migration path to it.

## Maintaining this matrix

Keep this page aligned with implemented source capabilities. Add one short developer
benefit for each capability; put operational detail in the linked
[workflow guides](../README.md#documentation). Update the review date when changing
the matrix. Release notes and candidate-specific verification identify what is in a
particular distribution; historical ZIPs under `downloads/` are not release evidence
for a different artifact.
