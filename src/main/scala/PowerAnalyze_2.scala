import org.apache.spark.{SparkConf, SparkContext}

object PowerAnalyze_2 extends  App{

  val conf = new SparkConf().setAppName("PowerAnalyze_2")
    .setMaster("local[*]")

  val sc = new SparkContext(conf)

  val Original_Data = sc.textFile("hdfs://140.128.98.31:8020/user/hpc/powerminute") //取資料格式為RDD
  val array = sc.textFile("hdfs://140.128.98.31:8020/user/hpc/powerminute").take(Original_Data.count.toInt) //RDD轉array
  val Data_Length = Original_Data.count().toInt
  val Data_Widgth = array(0).split(",",0).length
  val array2 = Array.ofDim[String](Data_Length, Data_Widgth)

  for (i <- 0 to array2.length - 1) {

    array2(i) = array(i).split(",", 0) //將切完字串後的資料放在陣列裡面

  }

  for (i <- 0 to array2.length-2){
    if(array2(i)(0).substring(1,11).toLong - array2(i+1)(0).substring(1,11).toLong > 600){
      println(array2(i)(0) + "發生斷電!!" + " 電錶ID為" + )
    }
  }


}
