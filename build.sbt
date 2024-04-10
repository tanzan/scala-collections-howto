import Dependencies._

ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.intellias"
ThisBuild / organizationName := "Intellias"

lazy val Benchmark = config("bench") extend Test

configs(Benchmark)
inConfig(Benchmark)(Defaults.testSettings)

lazy val root = (project in file("."))
  .settings(
    name := "scala-collections-howto",
    libraryDependencies ++= scalatest,
    libraryDependencies += scalameter,
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    Benchmark / parallelExecution  := false,
    Benchmark / fork := true,
    Benchmark / logBuffered  := false,
)

