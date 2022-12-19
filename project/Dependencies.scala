import sbt._

object Dependencies {

  object ver {
    val circe = "0.13.0"
    val akka = "2.6.17"
    val akkaHttp = "10.2.6"
    val akkaHttpCirce = "1.38.2"
    val config = "1.4.1"
    val scalajsDom = "2.0.0"
    val utest = "0.7.4"
    val scalaJsStubs = "1.1.0"
    val scalatest = "3.2.9"
    val slf4j = "2.0.5"
  }

  lazy val akkaHttp: Seq[ModuleID] =
    Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % ver.akka
      , "com.typesafe.akka" %% "akka-stream" % ver.akka
      , "com.typesafe.akka" %% "akka-http" % ver.akkaHttp
      , "com.typesafe.akka" %% "akka-http-spray-json" % ver.akkaHttp
      , "com.typesafe.akka" %% "akka-stream" % ver.akka
      , "de.heikoseeberger" %% "akka-http-circe" % "1.38.2"
    )

  lazy val scalaTest: Seq[ModuleID] = Seq("org.scalatest" %% "scalatest" % ver.scalatest % "test")

  lazy val typesafeConfig: Seq[ModuleID] = Seq("com.typesafe" % "config" % ver.config)

  lazy val logging: ModuleID = "org.slf4j" % "slf4j-nop" % ver.slf4j

}
