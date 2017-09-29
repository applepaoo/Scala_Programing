import org.apache.spark.{SparkConf, SparkContext}

object HdfsProcessData extends App{

  val conf = new SparkConf().setAppName("HdfsProcessData")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)

  val File1 = sc.textFile("hdfs://140.128.98.31:8020/user/hive/warehouse/powerdaily/part-m-00000")

  File1.sortBy(x => x).foreach(println)


}
