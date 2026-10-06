# Realistic Solutions community beta preparation

This checkout prepares **Ikasan Studio Community**, maintained by **Realistic Solutions**.
Publication is not authorised by this preparation task. The community candidate workflow
builds reviewable artifacts only; it does not create a public release or upload to Marketplace.

## Starting point — 6 October 2026

- Checkout: `/home/hidavi/dev/ws/IkasanStudio_community`, branch `main`, initially clean.
- Base: `e79a2b83`, matching the latest local `IkasanStudio_main` commit at inspection.
  The recent flow-test regeneration, rename and EDT fixes are present.
- `origin`: `git@github.com:RealisticSolutions/IkasanStudio.git`.
- `upstream`: `https://github.com/IkasanEIP/IkasanStudio.git`.
- Version: `1.0.0-beta.1`; compatibility boundaries remain as configured in `gradle.properties`.
- GitHub Issues was disabled and has been enabled for community feedback.
- GitHub Actions is enabled. No Actions secrets were listed at inspection; secret values were not read.
- The upstream checkout was read for commit comparison and was not edited.

## Confirmed community identity

The display name, vendor and public source/support links now identify the community fork.
The vendor homepage is the community GitHub repository.

The owner selected a separate community plugin ID on 6 October 2026:
`com.realisticsolutions.ikasanstudio.community`. `pluginGroup` in `gradle.properties`,
the main descriptor ID and the native MCP descriptor's plugin dependency use this ID.
The upstream ID remains `com.github.ikasaneip.ikasanstudio`.
Use the community ID for the first submission and retain it for later updates.

A separate ID does not establish safe simultaneous installation: both editions retain
shared action IDs, services, editor integration and optional content-module names.
Use separate IDE profiles for the upstream and community editions.

The owner confirmed `support@realisticsolutions.co.uk` as the public support email
on 6 October 2026. The vendor descriptor, README, security-report fallback and
Code of Conduct contact use this address. Ensure the mailbox is monitored for beta feedback.

The original BSD 3-Clause licence and Ikasan copyright are retained unchanged.
The README identifies the original project and the Realistic Solutions fork.
Existing Java packages, Maven coordinates and Ikasan API references retain their
technical identities; they are not vendor branding.

## Release configuration

`.github/workflows/release.yml` is now a manual **Community beta candidate** workflow.
It runs headless tests, plugin checks, packaging, archive auditing and Plugin Verifier,
then uploads the ZIP, SHA-256 checksums and reports as a GitHub Actions artifact.
It has read-only repository permissions and no Marketplace token.

The Build workflow's release-draft job is opt-in through the repository variable
`ENABLE_RELEASE_DRAFTS=true`. Leave it unset during preparation. Ordinary CI still runs.
Local workflow changes take effect on GitHub after they are committed and pushed.

Existing Gradle signing/publishing configuration uses `CERTIFICATE_CHAIN`, `PRIVATE_KEY`,
`PRIVATE_KEY_PASSWORD` and `PUBLISH_TOKEN`. Candidate preparation needs none of these.
Signing the submission candidate needs the three signing inputs; later automated
Marketplace updates would also need a token belonging to the community vendor.
Configure secrets in the intended repository or protected release environment; never
commit their values. Marketplace vendor ownership, account agreements, branch protection
and signing material remain owner tasks. No credentials were created by this preparation.

## Candidate validation and later submission

Follow [release-candidate verification](ReleaseCandidateVerification.md) and the
[manual checklist](MarketplaceReleaseManualChecklist.md), recording the exact archive hash.
Earlier upstream audit results do not qualify the community candidate.

```sh
./gradlew -p headless cleanTest test
./gradlew --no-configuration-cache cleanTest check buildPlugin verifyReleaseArchive verifyPlugin -PstudioSandboxDirectory=build/community-verification-sandbox
```

The full `check` gate includes existing BOM/help-URL and composite-build tasks that
are incompatible with Gradle's configuration cache. The candidate workflow disables
that cache explicitly; this keeps checks enabled and does not change normal build settings.

Install the exact candidate ZIP through **Install Plugin from Disk** in clean supported
IDE profiles. Record project creation/import, onboarding, editor close/reopen,
generation, Run/Debug, Blue Console, both meta-packs, flow tests, migration, AI routes,
multi-project isolation and uninstall results. For runtime delivery checks, verify first
delivery, idle readiness and later delivery without restarting the flow/application.

If signing changes the ZIP, audit and exercise that signed archive and record its hash.
After explicit publication authorisation, create a GitHub prerelease containing that
same tested ZIP and its checksum, then manually submit the same archive to the Realistic
Solutions Marketplace vendor. Confirm the intended beta channel and initial listing
visibility using the [Marketplace release plan](MarketplaceReleasePlan.md).
No GitHub prerelease or Marketplace submission has been made by this task.

## Local verification evidence — 6 October 2026

These results describe the community changes applied over base commit `e79a2b83`.
The changes were not committed or pushed during preparation.

- `./gradlew -p headless test`: passed, 475 tests, no failures/errors/skips.
- Final `./gradlew --no-configuration-cache check buildPlugin verifyReleaseArchive verifyPlugin -PstudioSandboxDirectory=build/community-verification-sandbox`: passed.
- Plugin tests: 1,361 tests, no failures/errors/skips, including the community-ID error-reporter registration check.
- Meta-pack BOM/help-URL verification and pack validation: passed.
- Archive audit: passed, 1,068 runtime resources and 515 Studio classes.
- Packaged descriptor: community ID/name, Realistic Solutions vendor, confirmed support email and repository URL checked directly inside the ZIP.
- Packaged `META-INF/LICENSE.txt`: byte-for-byte equal to the unchanged root BSD licence.
- Headless settings startup: loaded the community plugin; this does not replace installation/workflow acceptance.
- Changed GitHub workflow files parsed successfully; `git diff --check` passed.

| IDE | Plugin Verifier verdict | API warnings |
| --- | --- | --- |
| IDEA Community 2024.2 (`242.20224.300`) | Compatible | 3 deprecated, 8 experimental |
| IDEA Community 2024.3.7 (`243.28141.18`) | Compatible | 3 deprecated, 8 experimental |
| IDEA 2026.2.2 (`262.10315.125`) | Compatible | 21 deprecated, 8 experimental, 1 scheduled for removal |

The older IDEs lack the optional native MCP dependency; the main plugin remains compatible.
The API warnings remain maintenance work and are not evidence of future IDE compatibility.
Gradle also reports deprecated features that will need attention before Gradle 10.
The first full gate reached compatible verifier results but failed storing the configuration
cache; the final successful gate disabled that cache as documented above.

Local archive: `build/distributions/ikasanstudio-1.0.0-beta.1.zip` (unsigned).
Checksum file: `build/distributions/ikasanstudio-1.0.0-beta.1.zip.sha256`.

```text
16a896049131329ecd9ea44f4723cd15b6b30217d1aed8d0c75032addb5606a0  ikasanstudio-1.0.0-beta.1.zip
```

Reports are under `build/reports/tests/test/`, `build/reports/release/archive.json`,
`build/reports/pluginVerifier/` and `headless/*/build/reports/tests/test/`.
Clean-profile Install from Disk, interactive workflows, signing, vendor account setup
and later Marketplace installation remain unverified. Rebuilding or signing changes
the candidate; record the new archive hash and recheck it before submission.
