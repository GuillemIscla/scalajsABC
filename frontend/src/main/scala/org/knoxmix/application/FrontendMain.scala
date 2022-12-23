package org.knoxmix.application

import org.knoxmix.domain.UserService
import org.knoxmix.view.MainPageView
import org.scalajs.dom._

object FrontendMain extends App {

  println("Hello Scala.js World!")

  document.title = "My first Scala.js page"

  val userService = new UserService()
  val mainPageView = new MainPageView(userService)




}
