import java.io.File

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Analysis_PowerRecord extends App{

  val conf = new SparkConf().setAppName("HdfsProcessData")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)

  val warehouseLocation = new File("140.128.98.31:8020/user/hive/warehouse/").getAbsolutePath

  val spark = SparkSession
    .builder()
    .appName("Spark Hive Example")
    .config("spark.sql.warehouse.dir", warehouseLocation)
    .enableHiveSupport()
    .getOrCreate()

  //spark.sql("show tables").show()
}
