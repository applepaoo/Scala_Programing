
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


object PowerAnalyze extends App{

  val conf = new SparkConf().setAppName("test")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)


  val spark = SparkSession
    .builder()
    .appName("Spark Hive Example")
    .enableHiveSupport()
    .getOrCreate()

  val df = spark.sql("SELECT  unix_timestamp(concat(`date`, ' ',  substr(cast((`hr`+100) as STRING), 2), ':', substr(cast((`min`+100) as STRING), 2), ':00')), `meter_id` FROM powerminute where `meter_id` = 'CH-WIRED-2' GROUP BY unix_timestamp(concat(`date`, ' ',  substr(cast((`hr`+100) as STRING), 2), ':', substr(cast((`min`+100) as STRING), 2), ':00')), `meter_id` ORDER BY `meter_id` DESC, unix_timestamp(concat(`date`, ' ',  substr(cast((`hr`+100) as STRING), 2), ':', substr(cast((`min`+100) as STRING), 2), ':00')) desc")
  val data_RDD = df.rdd
  val data = data_RDD.map(_.mkString(",")).take(data_RDD.count().toInt).map(x => x.split(","))
  import java.text.SimpleDateFormat

  import scala.collection.mutable.ArrayBuffer
  //var k = 0;
  val data_ArrayBuffer = new ArrayBuffer[Long]()
  for( i <- 0 to data.length-2) {

    if(data(i)(0).toLong - data(i+1)(0).toLong > 180 &&  data(i)(0).toLong - data(i+1)(0).toLong < 10800 ){
      val df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val date = df.format(data(i+1)(0).toLong * 1000L)//發生斷電
      val date_1 = df.format(data(i)(0).toLong * 1000L)//復電
      val total_time = (data(i)(0).toLong - data(i+1)(0).toLong)/60

      data_ArrayBuffer += data(i+1)(0).toLong//斷電時間點_奇數
      data_ArrayBuffer += data(i)(0).toLong//復電時間點_偶數

      println(date + "," + data(i+1)(0) +"斷電,於" + date_1 + "復電,共斷電了" + total_time + "分鐘")
      //k = k + 1
    }
  }
  println("預估共斷電了" + data_ArrayBuffer.length/2 + "次")




}
