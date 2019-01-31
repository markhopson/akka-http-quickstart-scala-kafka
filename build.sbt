lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion    = "2.5.11"

name := "akka-http-quickstart-scala-kafka"

organization := "com.example"

version := "1.0.0"

scalaVersion := "2.12.4"

libraryDependencies += "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream"          % akkaVersion

libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test
libraryDependencies += "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test
libraryDependencies += "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test

libraryDependencies += "org.apache.kafka"  %% "kafka" % "1.0.0"

libraryDependencies += "com.lightbend"     %% "kafka-streams-scala"  % "0.2.0"
libraryDependencies += "com.lightbend"     %% "kafka-streams-query"  % "0.1.1"

libraryDependencies += "com.typesafe.akka" %% "akka-actor"           % akkaVersion

//"org.slf4j" %% "slf4j-simple" % "1.6.4",
