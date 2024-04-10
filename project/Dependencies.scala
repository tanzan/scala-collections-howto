import sbt._

object Dependencies {
  lazy val scalatest =  Seq(
    "org.scalactic" %% "scalactic" % "3.2.18" % "test",
    "org.scalatest" %% "scalatest" % "3.2.18" % "test"
  )

  lazy val scalameter = "com.storm-enroute" %% "scalameter" % "0.21"
}
