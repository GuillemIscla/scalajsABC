package org.knoxmix.view

import org.knoxmix.domain.UserService
import org.scalajs.dom.{Element, HTMLButtonElement, document}
import scalajs.concurrent.JSExecutionContext.Implicits.queue

class MainPageView(userService:UserService) {
  val root: Element = attachNewElement("div", document.body, Map("class" -> "container wrapper"))
  val top: Element = attachNewElement("top", root, Map("id" -> "top"))
  val welcome: Element = attachNewElement("h1", top)
  welcome.textContent = "Welcome to the ScalaABC webapp"
  val thisIs: Element = attachNewElement("p", top)
  thisIs.textContent = "This is the content of the webapp"
  val wrapper: Element = attachNewElement("div", root, Map("className" -> "wrapper"))
  val main: Element = attachNewElement("div", wrapper, Map("id" -> "main"))
  val thisPage: Element = attachNewElement("h1", main)
  thisPage.textContent = "This page has been created with Scala.js"
  val paragraph1: Element = attachNewElement("p", main)
  paragraph1.innerHTML = "The scala.js code is in the same project as the server using a multi-project model. " +
    "Other webapps can be added in the same app and is easy to share code between them as well with some code in the " +
    "server. That includes Json code, so, whatever parsing you want to use, you just need to code it once for both " +
    "frontend and backend."
  val paragraph2: Element = attachNewElement("p", main)
  paragraph2.innerHTML = "And of course frontend can communicate with the backend server:"
  val messageDiv:Element = attachNewElement("div", main)
  attachNewElement("hr", main)
  val button: HTMLButtonElement = attachNewElement("button", main, Map("className" -> "btn btn-outline-success my-2 my-sm-0")).asInstanceOf[HTMLButtonElement]
  button.onclick = { _ => messageDiv.textContent = "Button clicked"}
  button.textContent = "Get User"
  val bottom:Element = attachNewElement("div", main, Map("className" -> "bottom"))
  bottom.textContent = "Created by Guillem Iscla"

}