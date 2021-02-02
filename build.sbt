name := "neo4j-spark-scala-j11"

version := "0.1"

scalaVersion := "2.12.13"

resolvers ++= Seq(
  "Spark Packages Repo" at "https://dl.bintray.com/spark-packages/maven"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.1.0",
  "org.neo4j" % "neo4j-kernel" % "4.2.3",
  "neo4j-contrib" % "neo4j-spark-connector" % "2.4.5-M2",
  "graphframes" % "graphframes" % "0.8.1-spark3.0-s_2.12"
)