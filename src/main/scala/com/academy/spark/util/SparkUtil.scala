package com.academy.spark.util

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkUtil {

  def readCSV(sc: SparkSession, path: String): DataFrame = sc.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load(path)
}
