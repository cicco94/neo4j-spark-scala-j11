package com.academy.neo4j.util

import com.academy.spark.util.SparkUtil
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.neo4j.spark.Neo4j
import org.neo4j.spark.dataframe.Neo4jDataFrame

object Neo4jUtil {

  def loadCSV(ss: SparkSession, pathString: String, label:String): Unit = {
    val countryContinentDF: DataFrame = SparkUtil.readCSV(ss, pathString)
    Neo4jDataFrame.createNodes(ss.sparkContext, countryContinentDF, (label, countryContinentDF.columns.toSeq))
  }

  def createMatch(sc: SparkContext, query:String): Unit = Neo4j(sc).cypher(query, Map())
}
