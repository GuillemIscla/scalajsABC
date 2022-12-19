package org.knoxmix.view

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives
import org.knoxmix.infrastructure.AkkaConfig

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration.DurationInt
import scala.io.Source

class BackendHttpServer(akkaConfig: AkkaConfig) extends Directives {

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContextExecutor = system.executionContext

  private val routes =
    path("scalajs-rocks") {
      get {
        val html = Source.fromResource("public/index.html").mkString
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, html))
      }
    } ~
    path("assets" / Remaining) { file =>
      get {
        encodeResponse {
          getFromResource(resourceName = "public/" + file)
        }
      }
    }

  private val bindingFuture = Http().newServerAt(akkaConfig.host, akkaConfig.port).bind(routes)
    .map(_.addToCoordinatedShutdown(hardTerminationDeadline = 10.seconds))

  println(s"Server online at http://${akkaConfig.host}:${akkaConfig.port}/")

}