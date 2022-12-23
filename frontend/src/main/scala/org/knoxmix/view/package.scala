package org.knoxmix

import org.scalajs.dom.{Element, document}

package object view {
  def attachNewElement(tagName:String, parent:Element, attributes:Map[String, String] = Map.empty):Element = {
    val newElement = document.createElement(tagName)
    parent.appendChild(newElement)
    attributes.foreach{
      case (label, value) => newElement.setAttribute(label, value)
    }
    newElement
  }
}
