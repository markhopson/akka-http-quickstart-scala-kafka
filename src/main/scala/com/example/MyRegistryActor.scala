package com.example

import akka.actor.{ Actor, ActorLogging, Props }

object MyRegistryActor {
  final case class ActionPerformed(value: String)
  final case class GetKey(name: String)

  def props: Props = Props[MyRegistryActor]
}

class MyRegistryActor extends Actor with ActorLogging with OKTable {
  import MyRegistryActor._

  def receive: Receive = {
    case GetKey(name) =>
      sender() ! ActionPerformed(s"${localStore.get(name)}")
  }

}
