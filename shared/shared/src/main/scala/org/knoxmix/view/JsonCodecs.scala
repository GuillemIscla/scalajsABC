package org.knoxmix.view

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import org.knoxmix.domain.User

object JsonCodecs {

  implicit val userCodec:Codec[User] = deriveCodec

}
