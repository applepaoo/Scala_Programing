import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, LongType, StringType, StructField, StructType, TimestampType}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.streaming.kafka010._



object Spark_Streaming_Hive_Table {

  val spark = SparkSession
    .builder()
    .appName("Spark_Streaming_Hive_Table")
    .config("spark.sql.warehouse.dir", "/user/hive/warehouse/")
    .enableHiveSupport()
    .getOrCreate()

  val sc = new SparkConf().setAppName("Spark_Streaming_Hive_Table")
  val ssc = new StreamingContext(sc, Seconds(60))

  val topicMap = "powerdata_minute".split(":").map((_, 1)).toMap
  val zkQuorum = "140.128.98.31:2181";
  val group = "test-consumer-group"
  val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
  val lines_split = lines.map(x => x.split(",")).map( x => Row.fromSeq( x ) )
  val smStruct = StructType( (0 to 2).toList.map( x => x.toString).map( y => StructField( y , StringType, true ) ) )

  lines_split.foreachRDD( rdd => {
    val smDF = spark.createDataFrame( rdd, smStruct )
    smDF.("sm")
    val smTrgPart = spark.sql("insert into table powerdata_minute select * from sm")
  } )

  lines_split.print()
  ssc.start()
  ssc.awaitTermination()
}
