import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Kafka_producer extends App{

  val TOPIC = "RH"

  val props = new Properties()

  props.put("bootstrap.servers","140.128.98.31:9092") //設定zookeeper
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")





  val producer = new KafkaProducer[String, String](props)


  for(i <- 1 to 10){

    val record = new ProducerRecord(TOPIC, "testing", s" $i")
    producer.send(record)
    println(record)

  }



  producer.close()
  println("傳送完畢!")


}
