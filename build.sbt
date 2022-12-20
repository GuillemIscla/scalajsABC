import sbt.Keys._
import sbt._
import Settings._

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .in(file("shared"))
  .settings(sharedSettings())
  .settings(libraryDependencies ++= Seq(
    "io.circe" %%% "circe-core" % Dependencies.ver.circe
    , "io.circe" %%% "circe-generic" % Dependencies.ver.circe
    , "io.circe" %%% "circe-parser" % Dependencies.ver.circe
  ))
  .jsSettings(libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % Dependencies.ver.utest % "test"
    , "org.scala-js" %%% "scalajs-dom" % Dependencies.ver.scalajsDom
    , "org.scala-js" %% "scalajs-stubs" % Dependencies.ver.scalaJsStubs % "provided"
  ))
  .jvmSettings(libraryDependencies ++=
    Dependencies.akkaHttp
      ++ Dependencies.scalaTest
      ++ Dependencies.typesafeConfig
  )

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val backend = (project in file("backend"))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .dependsOn(sharedJvm)
  .settings(sharedSettings())
  .settings(
    libraryDependencies ++= (sharedJvm / libraryDependencies).value
      ++ Seq(Dependencies.logging)
    , run / fork := true
    , Compile / packageBin / mappings ++= {
      Seq(
        (frontend / Compile / fastOptJS).value
      )
        .flatMap(attFile => Seq(attFile.data, attFile.metadata(scalaJSSourceMap)))
        .map { f => (f, s"public/js/${f.getName}")}
    }

  )

lazy val frontend = (project in file("frontend"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(sharedJs)
  .settings(sharedSettings())
  .settings(sharedSettingsJS(mainClassName = "FrontendMain"))