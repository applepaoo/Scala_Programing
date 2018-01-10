import org.apache.spark.{SparkConf, SparkContext}

object PowerAnalyze extends App{

  val conf = new SparkConf().setAppName("HdfsProcessData")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)

  val test = sc.textFile("hdfs://140.128.98.31:8020/user/hpc/test")
  val array = sc.textFile("hdfs://140.128.98.31:8020/user/hpc/test").take(test.count.toInt)
  val data_length = test.count().toInt
  val data_widgth = array(0).split(",",0).length
  val array2 = Array.ofDim[String](data_length,data_widgth)

  for (i <- 0 to array2.length - 1) {

    array2(i) = array(i).split(",", 0) //將切完字串後的資料放在陣列裡面

  }

  for (i <- 0 to array2.length-2){
    if(array2(i)(3).toLong - array2(i+1)(3).toLong > 600){
      println(array2(i)(0).substring(1,11) + "," + array2(i)(1) + "時" + array2(i)(2) + "分發生斷電!!")
      //println(array2(i)(0)+"," + array2(i)(1) + array2(i)(2) + "," +array2(i)(3))
    }
  }




}
