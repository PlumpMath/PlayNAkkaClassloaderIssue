package com.app.util

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Extension
import akka.actor.ExtensionId
import akka.actor.ExtensionIdProvider
import akka.actor.ExtendedActorSystem
import akka.routing.Listeners
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.{ implicitConversions, postfixOps }

class Main(system: ActorSystem) extends Extension {
  val master = system.actorOf(Props(new Master()), name = "master")

  class Master extends Actor {

    override def preStart() {
      super.preStart()
      // create the schema
      context.watch(system.actorOf(Props[SchemaActor], name = "SCHEMA_PATH"))
    }

    def receive = {
      case _ ⇒
    }
  }
}

object Main extends ExtensionId[Main] with ExtensionIdProvider {
  override def lookup = this
  override def createExtension(system: ExtendedActorSystem) = new Main(system)
}

class SchemaActor extends Actor with Listeners {
  def receive = {
    case _ => println("test")
  }
}