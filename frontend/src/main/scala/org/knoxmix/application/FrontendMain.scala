package org.knoxmix.application

import io.circe.parser.decode
import scalajs.concurrent.JSExecutionContext.Implicits.queue
import org.scalajs.dom._

object FrontendMain extends App {

  println("Hello Scala.js World!")

  document.title = "My first Scala.js page"

  val myDiv = document.createElement("div")
  myDiv.setAttribute("class", "container-fluid")
  myDiv.textContent = "My first Scala.js page is here!"
  document.body.appendChild(myDiv)

  for {
    response <- fetch(s"getUser").toFuture
    text <- response.text().toFuture
  } yield {
    decode[User](text) match {
      case Right(user) => println(
        s"I got a user! This is his name: '${user.name} ${user.surname}' " +
          s"and he is ${user.age} years old")
      case Left(error) => throw new Exception(s"Could not parse user, got: '${error.getMessage}'")
    }
  }


}
