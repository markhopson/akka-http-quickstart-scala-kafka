package com.example

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.event.Logging

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.delete
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.server.directives.PathDirectives.path

import scala.concurrent.Future
import com.example.MyRegistryActor._
import akka.pattern.ask
import akka.util.Timeout

trait MyRoutes extends JsonSupport {

  // we leave these abstract, since they will be provided by the App
  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[MyRoutes])

  // other dependencies that MyRoutes use
  def myRegistryActor: ActorRef

  // Required by the `ask` (?) method below
  implicit lazy val timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  lazy val MyRoutes: Route =
    pathPrefix("keys") {
      concat(
        path(Segment) { name =>
          concat(
            get {
              val userCreated: Future[ActionPerformed] =
                (myRegistryActor ? GetKey(name)).mapTo[ActionPerformed]
              onSuccess(userCreated) { performed =>
                complete((StatusCodes.OK, performed))
              }
            })
        })
    }
}
