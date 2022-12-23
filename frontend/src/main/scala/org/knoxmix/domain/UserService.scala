package org.knoxmix.domain

import io.circe.parser.decode
import org.knoxmix.view.JsonCodecs._
import org.scalajs.dom.fetch
import scala.concurrent.Future
import scalajs.concurrent.JSExecutionContext.Implicits.queue

class UserService {

  def getUser:Future[String] = {
    for {
      response <- fetch(s"getUser").toFuture
      text <- response.text().toFuture
    } yield {
      decode[User](text) match {
        case Right(user) =>
          s"I got a user! This is his name: '${user.name} ${user.surname}' " +
            s"and he is ${user.age} years old"
        case Left(error) => throw new Exception(s"Could not parse user, got: '${error.getMessage}'")
      }
    }
  }

}