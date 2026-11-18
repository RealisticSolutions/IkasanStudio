# Community beta.3 candidate — 18 November 2026

- Distribution: **Ikasan Studio Community 1.0.0-beta.3**.
- Repository: https://github.com/RealisticSolutions/IkasanStudio
- Plugin ID: `com.realisticsolutions.ikasanstudio.community`; Marketplace channel: `default`, preserving beta.2 configuration. GitHub prerelease status is independent of the Marketplace channel.
- Source: community `cfe23815` merged with upstream `a4ff8a5cb012dd11f9d8d6036bec6be927f8caa6`; merge commit `6780c187`.
- Supported packs: V3.3.9 / Java 11, V4.1.6 / Java 17, V5.1.0 revision 1.0.8 / Java 25.
- Verifier boundaries: IDEA 2024.2, 2024.3.7 and 2026.3. The [JetBrains release feed](https://data.services.jetbrains.com/products/releases?code=IIU&latest=true&type=release) identified 2026.3, build 263.7307.200, released 5 November 2026.
- Status: automated candidate gates passed; interactive checks and signing remain outstanding.

The community workflow remains manual and produces candidate artifacts; it does not automatically publish to Marketplace. Existing community compatibility fixes and branding are retained.

Before publication, complete the [candidate gates](ReleaseCandidateVerification.md) and [manual checklist](MarketplaceReleaseManualChecklist.md), including upgrade from the actual beta.2 artifact, all three packs, source import, model repair, shared harness ownership, Run/Debug, themes/scaling and disposal. Windows/macOS and interactive checks require explicit evidence; earlier results do not certify this candidate. Signing and Marketplace upload remain separate release steps.

## Automated evidence

- Verified implementation and packaged metadata: `8ac4baf1`. Subsequent commits update release/installation documentation and record these results; no runtime payload changed.
- Fresh standalone run: `./gradlew -p headless cleanTest test --offline` — **709 tests, zero failures**.
- Full community gate: `./gradlew --no-configuration-cache cleanTest check buildPlugin verifyReleaseArchive verifyPlugin -PstudioSandboxDirectory=build/community-beta3-verification-sandbox` — **PASS**, 7m 7s.
- Plugin suite: **1,891 tests, zero failures**. Full check also passed headless contracts, all three meta-pack BOM checks and help URL checks.
- Fresh isolated searchable-options generation and plugin packaging passed.
- Archive audit: **PASS**, 1,500 source resources and 677 Studio classes, released V3/V4/V5 packs present, community metadata checked.
- Binary compatibility: **Compatible** on IC-242.20224.300, IC-243.28141.18 and IU-263.7307.200. Verifier reports 13 experimental API usages on each boundary and 19 deprecated API usages on 2026.3; these are findings, not incompatibility errors. Optional native MCP is unavailable on the older IDEs as expected.

## Exact unsigned candidate

Archive: `ikasanstudio-1.0.0-beta.3.zip`.

```text
57756822bbdc33f16719b1632a34a893f13358ef424046fda412f8752c5c18ea  ikasanstudio-1.0.0-beta.3.zip
```

This archive is unsigned. Signing changes the artifact: audit the signed ZIP, record its new checksum and use that exact artifact for the final installation/upgrade checks and Marketplace submission.

The stored beta.2 upgrade artifact was verified against its committed checksum:

```text
0ca420dfcf5af47be41eeb5ef94cdb809ccf7a6ea0704edeadd1edeb957e8efc  ikasanstudio-1.0.0-beta.2.zip
```

The checksum check does not establish that an interactive upgrade was performed. No Linux/Windows/macOS manual matrix, Marketplace upload or signing completion is claimed by this audit.
