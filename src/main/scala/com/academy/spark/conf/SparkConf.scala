package com.academy.spark.conf

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object SparkConf {
  private val APP_NAME: String = "NEO4J_SPARK_SCALA_J11"

  def getLocalSparkContext: SparkContext = new SparkContext(
    new SparkConf()
      .setAppName(APP_NAME)
      .setMaster("local")
      .set("spark.driver.host", "127.0.0.1")
  )

  def getLocalSparkSession: SparkSession = SparkSession.builder()
    .appName(APP_NAME)
    .master("local")
    .config("spark.driver.host", "127.0.0.1")
    .getOrCreate()
}
