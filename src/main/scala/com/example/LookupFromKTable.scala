package com.example

import scala.concurrent.{ ExecutionContext, Future }

trait LookupFromKTable extends OKTable with ((String) => Future[String]) {

  implicit def ex: ExecutionContext

  override def apply(key: String): Future[String] = Future(localStore.get(key))
}
