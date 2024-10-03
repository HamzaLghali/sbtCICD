import Dependencies._

ThisBuild / scalaVersion := "2.13.14"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"
ThisBuild / git.gitTagToVersionNumber := { tag: String =>
  if (tag matches "[0-9]+\\..*") {
    Some(tag)
  } else {
    None
  }
}

lazy val root = (project in file("."))
  .enablePlugins(GitVersioning)
  .enablePlugins(GitlabPlugin)
  .settings(
    name := "test-project",
    assembly / assemblyJarName := "app.jar",
    assembly / test := {},
    git.baseVersion := "0.1.0",
    git.useGitDescribe := true,
    gitlabProjectId := Some(GitlabProjectId("6478")),
    gitlabDomain := "gitlab.switch.ch",
    libraryDependencies += munit % Test
  )
