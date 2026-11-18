# Installing Ikasan Studio Community

The **1.0.0-beta.3** candidate is being prepared; see its [verification audit](ReleaseAudit-2026-11-18.md). Install an approved candidate ZIP or [build the plugin](../README.md#build-and-contribute). See the [README release status](../README.md) and [supported versions](SupportedVersions.md).

## Marketplace releases

Once published, use the official listing linked from this repository. Check the publisher, version and compatibility before installing, then restart IntelliJ if prompted.

The community distribution uses Marketplace's **default channel**, retaining the beta.2 configuration. The `beta` suffix in the version does not require adding a custom plugin repository. Search for **Ikasan Studio Community** and check the **Realistic Solutions** vendor. GitHub prerelease status is separate from Marketplace channel selection.

## Candidate ZIP or offline installation

Obtain the candidate ZIP from the maintainers, or build it into `build/distributions/`. In IntelliJ choose **Settings → Plugins → gear → Install Plugin from Disk**, select the ZIP and restart if prompted. Keep its version and source so feedback identifies the build you tested. Installing an exact candidate ZIP remains part of release verification.

Offline installation does not make project creation or builds offline: Maven dependencies, archetypes and optional harness downloads must already be available locally.

## Next steps

Run IntelliJ with its supplied runtime. Install and select Java 11 for an Ikasan 3.3.9 application, Java 17 for an Ikasan 4.1.6 application or Java 25 for an Ikasan 5.1.0 application; the project SDK is separate from the IDE runtime. Continue with [Your first module](GettingStarted.md), [known limitations](KnownLimitations.md) and the [video tutorials](../tutorials/README.md).

Ikasan Studio is distributed under the [BSD 3-Clause License](../LICENSE.txt). Third-party dependencies retain their own licences and notices.
