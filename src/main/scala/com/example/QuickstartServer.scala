package com.example

//#quick-start-server
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

object QuickstartServer extends App with MyRoutes {

  // set up ActorSystem and other dependencies here
  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val myRegistryActor: ActorRef = system.actorOf(MyRegistryActor.props, "myRegistryActor")

  // from the MyRoutes trait
  lazy val routes: Route = MyRoutes

  Http().bindAndHandle(routes, "localhost", 8080)

  println(s"Server online at http://localhost:8080/")

  Await.result(system.whenTerminated, Duration.Inf)
}
