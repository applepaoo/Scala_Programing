import java.util.Properties
import java.util
import scala.collection.JavaConverters._


import org.apache.kafka.clients.consumer.KafkaConsumer

object Kafka_Consumer extends App{

  val TOPIC = "HPC"

  val props = new Properties()
  props.put("bootstrap.servers", "140.128.98.31:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "something")

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(util.Collections.singletonList(TOPIC))

  while(true){
    val records=consumer.poll(100)
    for (record<-records.asScala){
      println(record)
    }
  }
}
