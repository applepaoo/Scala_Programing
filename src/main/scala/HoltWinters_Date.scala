import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql._

object HoltWinters_Date extends App {

  def getNowDate(): String = {
    //取得現在時間
    var now: Date = new Date()
    var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var hehe = dateFormat.format(now)
    hehe
  }

  def determineDayOfTheWeek(a: Int): Int = {
    //取得星期幾
    var dayForWeek = 0
    if (a == 1) {
      dayForWeek = 7
      return dayForWeek
    } else {
      dayForWeek = a - 1
      return dayForWeek
    }
  }

  val sdf = new SimpleDateFormat("yyyy-MM-dd")
  val cal = java.util.Calendar.getInstance();
  cal.setTime(sdf.parse(getNowDate()))
  cal.add(java.util.Calendar.DATE, -7) //往前一星期

  println("現在日期:" + getNowDate())
  println("上週日期:" + sdf.format(cal.getTime))

  val day = determineDayOfTheWeek(cal.get(Calendar.DAY_OF_WEEK)) //找出星期幾


  if (day == 6 || day == 7 || day == 1 || day == 2) {
    println("今天星期" + day + "需要使用上週資料")

  } else {
    println("今天星期" + day + "不需使用上週資料")
  }

/*
  val conf = new SparkConf().setAppName("Simple Application")
  val sc = new SparkContext(conf)
  val spark = SparkSession
    .builder()
    .appName("Spark Hive Example")
    .enableHiveSupport()
    .getOrCreate()

  val jdbcDF = spark.read.format("jdbc").option("url", "jdbc:mysql://120.109.150.175:3306/power").option("driver","com.mysql.jdbc.Driver").option("dbtable", "PowerHour").option("user", "hpc").option("password", "hpcverygood").load()
*/



}
