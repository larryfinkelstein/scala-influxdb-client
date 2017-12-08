name := "scala-influxdb-client"

organization := "com.paulgoldbaum"

scalaVersion := "2.12.0"
crossScalaVersions := Seq("2.12.0", "2.11.8")

testOptions in Test += Tests.Argument("-oDF")

useGpg := true
releaseCrossBuild := true

//libraryDependencies += "org.asynchttpclient" % "async-http-client" % "2.0.24"
libraryDependencies += "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.1.3"
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.2"
libraryDependencies += "com.github.tomakehurst" % "wiremock" % "2.3.1" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

import ReleaseTransformations._

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
  pushChanges
)
