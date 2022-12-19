package org.knoxmix.application

import com.typesafe.config.ConfigFactory
import org.knoxmix.infrastructure.BackendConfig
import org.knoxmix.view.BackendHttpServer

object BackendMain extends App {

  val config = BackendConfig.load(ConfigFactory.load())

  new BackendHttpServer(config.akkaConfig)


}
