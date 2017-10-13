import com.google.common.eventbus.Subscribe
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010._


object test_Spark_Streaming_Kafka extends App{
/*
  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "localhost:9092,anotherhost:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "use_a_separate_group_id_for_each_stream",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val conf = new SparkConf().setAppName("Spark_Streaming_Kafka")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)

  val ssc = new StreamingContext(sc, Seconds(60))

  val topics = Array("topicA", "topicB")
  val stream = KafkaUtils.crea[String, String](
    streamingContext,
    PreferConsistent,
    Subscribe[String, String](topics, kafkaParams)
  )

  stream.map(record => (record.key, record.value))*/

}
