package com.academy

import com.academy.neo4j.util.Neo4jUtil
import com.academy.spark.conf.SparkConf
import org.apache.spark.sql.SparkSession

object ProduceData extends App {
  private val ss: SparkSession = SparkConf.getLocalSparkSession

  Neo4jUtil.loadOnNeo4J(ss, "C:\\Users\\cicco\\Projects\\neo4j-spark-scala-j11\\src\\main\\resources\\country-codes.csv", "Country")
  Neo4jUtil.loadOnNeo4J(ss, "C:\\Users\\cicco\\Projects\\neo4j-spark-scala-j11\\src\\main\\resources\\airport-codes.csv", "Airport")
  Neo4jUtil.loadOnNeo4J(ss, "C:\\Users\\cicco\\Projects\\neo4j-spark-scala-j11\\src\\main\\resources\\continent-codes.csv", "Continent")

}
