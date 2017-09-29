import org.apache.spark.{SparkConf, SparkContext}
object wordcount extends App{

  val conf = new SparkConf().setAppName("wordcount")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)
  val textFile = sc.textFile("Spark.txt")
  val counts = textFile.flatMap(x => x.split(" "))
    .map(x => (x, 1))
    .reduceByKey(_ + _).sortBy(_._2,false)foreach(println)

  val test = sc.parallelize(List(("apple", 5, 30.1), ("ihpone", 6, 28.1), ("mac", 10, 100.5)))

  test.map(x => (x._1, x._2 * x._3)).sortBy(_._2,false).foreach(println)


  val s = sc.textFile("hdfs://140.128.98.34:9000/alluxio/data/LICENSE.txt")

  s.take(10).foreach(println)

  println(10)
  scala.io.StdIn.readLine()




}
