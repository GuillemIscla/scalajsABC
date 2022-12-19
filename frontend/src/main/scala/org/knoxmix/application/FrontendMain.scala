package org.knoxmix.application

import org.scalajs.dom._

object FrontendMain extends App {

  println("Hello Scala.js World!")

  document.title = "My first Scala.js page"

  val myDiv = document.createElement("div")
  myDiv.setAttribute("class", "container-fluid")
  myDiv.textContent = "My first Scala.js page is here!"
  document.body.appendChild(myDiv)

}
