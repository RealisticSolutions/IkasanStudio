# Community beta.3 candidate — 18 November 2026

- Distribution: **Ikasan Studio Community 1.0.0-beta.3**.
- Repository: https://github.com/RealisticSolutions/IkasanStudio
- Plugin ID: `com.realisticsolutions.ikasanstudio.community`; Marketplace channel: `default`, preserving beta.2 configuration. GitHub prerelease status is independent of the Marketplace channel.
- Source: community `cfe23815` merged with upstream `a4ff8a5cb012dd11f9d8d6036bec6be927f8caa6`; merge commit `6780c187`.
- Supported packs: V3.3.9 / Java 11, V4.1.6 / Java 17, V5.1.0 revision 1.0.8 / Java 25.
- Verifier boundaries: IDEA 2024.2, 2024.3.7 and 2026.3. The [JetBrains release feed](https://data.services.jetbrains.com/products/releases?code=IIU&latest=true&type=release) identified 2026.3, build 263.7307.200, released 5 November 2026.
- Status: preparation in progress. Automated results and exact archive checksum remain to be recorded.

The community workflow remains manual and produces candidate artifacts; it does not automatically publish to Marketplace. Existing community compatibility fixes and branding are retained.

Before publication, complete the [candidate gates](ReleaseCandidateVerification.md) and [manual checklist](MarketplaceReleaseManualChecklist.md), including upgrade from the actual beta.2 artifact, all three packs, source import, model repair, shared harness ownership, Run/Debug, themes/scaling and disposal. Windows/macOS and interactive checks require explicit evidence; earlier results do not certify this candidate. Signing and Marketplace upload remain separate release steps.
