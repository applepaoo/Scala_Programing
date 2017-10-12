import org.apache.spark.SparkConf



object Spark_Streaming_Kafka extends App{

  val conf = new SparkConf().setAppName("Spark_Streaming_Kafka")
    .setMaster("local[*]")

/*  val ssc = new StreamingContext(conf, Seconds(60))


  val topics = List("TEST")
  val kafkaParams = Map[String, String]("metadata.broker.list" -> "140.128.98.31"
  )
  val messages = KafkaUtils.createDirectStream[String, String](
    ssc,
    LocationStrategies.PreferConsistent,
    ConsumerStrategies.Subscribe[String, String](topics, kafkaParams)
  )

  messages.print()

  ssc.start()
  ssc.awaitTermination()*/

/*  import org.apache.spark.streaming._
  import org.apache.spark.streaming.kafka._

  val sc = new SparkConf().setAppName("SparkStreamingKakfaWordCount")
  val ssc = new StreamingContext(sc, Seconds(60))
  val topicMap = "TEST".split(":").map((_, 1)).toMap
  //zookeeper quorums server list
  val zkQuorum = "140.128.98.31:2181";
  //consumer group
  val group = "test-consumer-group"
  val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
  lines.print()

  ssc.start()
  ssc.awaitTermination()*/










}

