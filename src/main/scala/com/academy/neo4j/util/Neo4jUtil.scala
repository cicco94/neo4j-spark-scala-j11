package com.academy.neo4j.util

import com.academy.spark.util.SparkUtil
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.neo4j.spark.dataframe.Neo4jDataFrame

object Neo4jUtil {

  def loadOnNeo4J(ss: SparkSession, pathString: String, label:String): Unit ={
    val countryContinentDF: DataFrame = SparkUtil.readCSV(ss, pathString)
    Neo4jDataFrame.createNodes(ss.sparkContext, countryContinentDF, (label, countryContinentDF.columns.toSeq))
  }
}
