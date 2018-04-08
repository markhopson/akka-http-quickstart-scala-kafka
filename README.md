# akka-http-quickstart-scala-kafka

A very simple RESTful API that serves data from a Kafka (Global)KTable

This project is a mash up of the following tutorials

* [akka-http-quickstart-scala](https://github.com/akka/akka-http-quickstart-scala.g8)
* [confluent/examples/streams/GlobalKTablesExample](https://www.javatips.net/api/examples-master/kafka-streams/src/main/java/io/confluent/examples/streams/GlobalKTablesExample.java)

## API Spec

```
# Query value with key from a KTable

GET /keys/${key}
{
  "value": ${ktableValue}
}
```

# Directory Structure

```sh
.
├── src/main/scala/com/example
|   ├── JsonSupport.scala           # 
|   └── OKTable.scala               # init KStream and KTable
|   └── QuickstartServer.scala      # main()
|   └── MyRegistryActor.scala     # 
|   └── MyRoutes.scala            # API routes
```

# Requirements

* Download and Install [Confluent Kafka](https://www.confluent.io/download)

```sh
# Start the console producer and send key:value message (e.g. "a:123")
# to the "test" topic
# 
kafka-console-producer \
  --broker-list localhost:9092 \
  --topic test \
  --property "parse.key=true" \
  --property "key.separator=:"

# Start a consumer (in another window) and watch your messages arrive
# 
kafka-console-consumer \
    --bootstrap-server localhost:9092 \
    --topic test \
    --from-beginning
```

# Building and Running

```
$ sbt
...
> run
...
```