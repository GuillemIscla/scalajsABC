package org.knoxmix.infrastructure

import com.typesafe.config.Config

case class AkkaConfig(host:String, port:Int)

case class BackendConfig(akkaConfig: AkkaConfig)

object BackendConfig {
  def load(mainConfig:Config):BackendConfig = {
    val akkaConfig = AkkaConfig(
      host = mainConfig.getString("akka.http.host")
      , port = mainConfig.getInt("akka.http.port")
    )

    BackendConfig(akkaConfig)
  }
}