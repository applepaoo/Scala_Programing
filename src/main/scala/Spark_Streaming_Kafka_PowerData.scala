import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Spark_Streaming_Kafka_PowerData extends App{

  val conf = new SparkConf().setAppName("Spark_Streaming_Kafka_PowerData")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)

  val ssc = new StreamingContext(sc, Seconds(60))

  val topicMap = "TEST".split(":").map((_, 1)).toMap

  val zkQuorum = "140.128.98.31:2181";

  val group = "test-consumer-group"

  val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)

  lines.print()

  ssc.start()

  ssc.awaitTermination()


}
