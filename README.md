# sbt/Scala GitLab CI/CD Components

[CI/CD components](https://docs.gitlab.com/ee/ci/components/) are reusable
single pipeline configuration units. This repository contains such units for
testing and building sbt / Scala applications. They are used in projects
maintained by the IT department of the [University Library of
Basel](https://ub.unibas.ch).

## Components

### test

Runs unit tests, optionally checks scalastyle rules and creates test reports

#### Requirements

In case stylechecking is activated:

- [Scalastyle plugin](http://www.scalastyle.org/sbt.html) is installed
- Scalastyle configuration file is generated

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `stage` | Stage in CI/CD | `string` | `"test"` |
| `check_style` | Check scalastyle rules | `boolean` | `false` |
| `additional_libs` | Additional libraries to installed in base image, separated by whitespace | `string` | `""` |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/test@main
```

#### Artifacts

- JUnit test reports in `target/test-reports`
- Test reports in pipeline


### test-coverage

Creates a test coverage report. Additionally, it extracts the information required for generating a [coverage badge](https://docs.gitlab.com/ee/user/project/badges.html#test-coverage-report-badges).

#### Requirements

[sbt-coverage plugin](https://github.com/scoverage/sbt-scoverage) installed

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `stage` | Stage in CI/CD | `string` | `"test"` |
| `additional_libs` | Additional libraries to installed in base image, separated by whitespace | `string` | `""` |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/test-coverage@main
```

#### Artifact

Coverage report in `target/cobertura.xml`


### build

Assemblies a "fat jar"

#### Requirements

[sbt-assembly](https://github.com/sbt/sbt-assembly) plugin needs to be installed

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `stage` | Stage in CI/CD | `string` | `"build"` |
| `additional_libs` | Additional libraries to installed in base image, separated by whitespace | `string` | `""` |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/build@main
```

#### Artifact

Binary (on `target/app`) with expiration date +1h


### build-play

Builds a universal package of a [Play](https://playframework.com) application

#### Requirements

[sbt-plugin](https://www.playframework.com/documentation/3.0.x/BuildOverview#Play-plugin-for-sbt-(/project/plugins.sbt)) plugin for Play applications needs to be installed

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `stage` | Stage in CI/CD | `string` | `"build"` |
| `additional_libs` | Additional libraries to installed in base image, separated by whitespace | `string` | `""` |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/build-play@main
```

#### Artifact

Package (on `target/app`) with expiration date +1h

#### Notes

This component is currently not tested


### publish-lib

Creates and publishes a library 

#### Requirements

[sbt-gitlab plugin](https://github.com/azolotko/sbt-gitlab) needs to be installed and the library registry set up in `build.sbt` (see plugin documentation for further information)

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `stage` | Stage in CI/CD | `string` | `"publish"` |
| `additional_libs` | Additional libraries to installed in base image, separated by whitespace | `string` | `""` |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/publish-lib@main
```

#### Artifact

Published library


### pages

Creates and deploys documentation as GitLab pages

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `additional_libs` | Additional libraries to installed in base image, separated by whitespace | `string` | `""` |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/pages@main
```

#### Artifact

- Documentation in folder `public/`
- Documentation as [GitLab pages](https://docs.gitlab.com/ee/user/project/pages/)


### sbt-default

Runs all components related to testing and building an application.

#### Inputs

| Inputs | Description | Value type | Default value |
| ------ | ----------- | ---------- | ------------- |
| `scala_version` | Scala version used in base image | `string` | `"2.13.14"` |

#### Example

```yaml
include:
  - component: $CI_SERVER_FQDN/ub-unibas/ci/sbt-components/sbt-default@main
```

#### Notes

- Requires stages `test`, `build` and `deploy` per default
- `test-coverage` only runs on default branch
- `pages` only runs on default branch
