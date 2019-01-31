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
import akka.util.Timeout

import StatusCodes.InternalServerError

import scala.util.{Failure, Success}

trait MyRoutes extends JsonSupport {

  // we leave these abstract, since they will be provided by the App
  implicit def system: ActorSystem

  // other dependencies that MyRoutes use
  def lookup: String => Future[String]

  // Required by the `ask` (?) method below
  implicit lazy val timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  lazy val MyRoutes: Route =
    pathPrefix("keys") {
      concat(
        path(Segment) { name =>
          concat(
            get {
              val eventualValue = lookup(name)
              onComplete(eventualValue) {
                case Success(value) => complete(KeyValue(name, value))
                case Failure(ex) => complete((InternalServerError, s"An error occurred: ${ex.getMessage}. Ex = $ex"))
              }
            })
        })
    }
}
