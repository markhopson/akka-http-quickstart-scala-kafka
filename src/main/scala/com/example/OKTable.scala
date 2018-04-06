package com.example

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.state.KeyValueStore
import org.apache.kafka.streams.state.QueryableStoreTypes

trait OKTable {

  val props = new Properties()
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application")
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
  props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass)
  props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass)
  val config = new StreamsConfig(props)

  val builder = new StreamsBuilder

  val myTopicGlobalTable = builder.globalTable(
    "test",
    Materialized.as[String, String, KeyValueStore[Bytes, Array[Byte]]]("my-store"))

  val streams = new KafkaStreams(builder.build(), config)
  streams.cleanUp()
  streams.start()

  lazy val localStore = streams.store("my-store", QueryableStoreTypes.keyValueStore[String, String])

}
