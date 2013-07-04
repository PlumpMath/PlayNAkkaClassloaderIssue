package com.app.util

import scala.collection.JavaConversions._
import akka.actor.{ ActorSystem, Extension, ExtensionId, ExtensionIdProvider, ExtendedActorSystem }
import akka.actor.Extension

class AkkaExtensions(system: ActorSystem) extends Extension {
  println("For test purposes-all that is required is an instance of Extension.")
   
  val `class` = Class.forName("com.app.util.AnExtension$")
  val eid = `class`.getField("MODULE$").get(`class`).asInstanceOf[ExtensionIdProvider]

  println(eid.lookup)
  system.registerExtension(eid.lookup)
 
}

object AkkaExtensions extends ExtensionId[AkkaExtensions] with ExtensionIdProvider {
  override def lookup = this
  override def createExtension(system: ExtendedActorSystem) = new AkkaExtensions(system)
}

class AnExtension(system: ActorSystem) extends Extension {
  println("For test purposes-all that is required is an instance of Extension.")
}

object AnExtension extends ExtensionId[AnExtension] with ExtensionIdProvider {
  override def lookup = this
  override def createExtension(system: ExtendedActorSystem) = new AnExtension(system)
}
