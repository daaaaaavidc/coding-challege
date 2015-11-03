import sbt._
import Keys._

object App extends Build {

  lazy val dependencies = Seq(
    "io.argonaut"       %% "argonaut"     % "6.1",
    "org.specs2"        %% "specs2-core"  % "3.6.5" % "test")

  lazy val baseSettings = Seq(
    organization  := "com.github.daaaaaavidc",
    name          := "codingChallenge",
    scalaVersion  := "2.11.6",
    scalacOptions in Test ++= Seq("-Yrangepos"),
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-unchecked",
      "-deprecation")
  )

  lazy val bintray =
    Seq("scalaz-bintray" at "http://dl.bintray.com/scalaz/releases")
  
  lazy val project =
    Project("codingChallenge", file("."))
      .settings(baseSettings: _*)
      .settings(resolvers ++= bintray)
      .settings(libraryDependencies ++= dependencies)
}
