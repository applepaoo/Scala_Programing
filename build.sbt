name := "Scala_Programing"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.0" //% "provided"

libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "0.10.2.0"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.2.0"

//libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.2.0" //% "provided"

libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.2.0" //% "test"