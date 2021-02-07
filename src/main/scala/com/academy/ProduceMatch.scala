package com.academy

import com.academy.neo4j.util.Neo4jUtil
import com.academy.spark.conf.SparkConf

object ProduceMatch extends App {

  private val countryBelongsToContinentQuery: String =
    "MATCH (c1:Country), (c2:Continent)" +
      "WHERE c1.continent=c2.code" +
      "CREATE (c1)-[:COUNTRY_BELONGS_CONTINENT]->(c2)"

  private val airportBelongsCountryQuery: String =
    "MATCH (c1:Country), (a:Airport)" +
      "WHERE c1.code=a.iso_country" +
      "CREATE (c1)-[:AIRPORT_BELONGS_COUNTRY]->(a);"

  Neo4jUtil.createMatch(SparkConf.localSparkContext, countryBelongsToContinentQuery)
  Neo4jUtil.createMatch(SparkConf.localSparkContext, airportBelongsCountryQuery)
}