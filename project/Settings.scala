import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._
import org.scalajs.jsenv.nodejs.NodeJSEnv
import sbt.Keys._

object Settings {

  val jsName = settingKey[String]("Name for the js file")

  lazy val orgId = "knoxmix"
  lazy val orgHomepage: Option[URL] = None
  lazy val projectName = "scalajsABC"

  object ver {
    val scalaLang = "2.13.10"
    val project = "0.2"
  }

  lazy val organizationSettings = Seq(
    organization := orgId
    , organizationHomepage := orgHomepage
  )

  def sharedSettings(): Seq[Def.Setting[_]] = Seq(
    scalaVersion := ver.scalaLang
    , version := s"${ver.project}"
    , packageDoc / publishArtifact := false
    , Compile / doc / sources := Seq.empty
    , scalacOptions += "-deprecation"
  ) ++ organizationSettings

  def sharedSettingsJS(mainClassName:String): Seq[Def.Setting[_]] = Seq(
    Compile / mainClass := Some(s"org.knoxmix.application.$mainClassName")
    , scalaJSUseMainModuleInitializer := true
    , jsEnv := new NodeJSEnv()
    , testFrameworks += new TestFramework("utest.runner.Framework")
  )

}
