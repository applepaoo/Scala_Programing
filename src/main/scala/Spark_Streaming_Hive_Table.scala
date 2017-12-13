import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}






object Spark_Streaming_Hive_Table extends App{

  val conf = new SparkConf().setAppName("Simple Application")
  val sc = new SparkContext(conf)
  val ssc = new StreamingContext(sc, Seconds(60)) //建立Streaming Context 接口
  val topicMap = "powerdata_minute".split(":").map((_, 1)).toMap  //指定kafka topic name
  val zkQuorum = "140.128.98.31:2181"; //ZooKeeper 位置
  val group = "test-consumer-group"
  val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2) //接kafka串流，形成DStream (Spark Streaming專用格式)
  val lines_split = lines.map(x => x.split(",")).map( x => Row.fromSeq( x ) ) //用逗號切割
  val smStruct = StructType( (0 to 2).toList.map( x => x.toString).map( y => StructField( y , StringType, true ) ) ) //為了下面dataframe 指定格式

  lazy val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc) //慵懶化sqlcontext
  lines_split.foreachRDD( rdd => {
    val smDF = sqlContext.createDataFrame( rdd, smStruct ) //DStream轉dataframe
    smDF.registerTempTable("sm")
    val smTrgPart = sqlContext.sql("insert into table powerdata_minute select * from sm") //寫入Hive Table
  } )

  lines_split.print() //切割後印出
  ssc.start()
  ssc.awaitTermination()
}


