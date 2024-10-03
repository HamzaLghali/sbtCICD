addSbtPlugin("com.github.sbt" % "sbt-git" % "2.0.1")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.2.0")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.12")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("nl.zolotko.sbt" % "sbt-gitlab" % "0.1.4")

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" % "scala-xml_2.12" % VersionScheme.Always
