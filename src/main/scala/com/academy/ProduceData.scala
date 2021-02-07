package com.academy

import com.academy.neo4j.util.Neo4jUtil
import com.academy.spark.conf.SparkConf

import java.io.File

object ProduceData extends App {
  private val canonicalPath: String = new File(".").getCanonicalPath

  Neo4jUtil.loadCSV(SparkConf.localSparkSession, canonicalPath+"\\src\\main\\resources\\country-codes.csv", "Country")
  Neo4jUtil.loadCSV(SparkConf.localSparkSession, canonicalPath+"\\src\\main\\resources\\airport-codes.csv", "Airport")
  Neo4jUtil.loadCSV(SparkConf.localSparkSession, canonicalPath+"\\src\\main\\resources\\continent-codes.csv", "Continent")

}