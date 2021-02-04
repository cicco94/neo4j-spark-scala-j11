import org.apache.spark.sql.{DataFrame, SparkSession}
import org.neo4j.spark.dataframe.Neo4jDataFrame

object SparkSessionInstancer {
  private val APP_NAME: String = "PHONE_COMPANY_EXERCISE"

  def getLocal(): SparkSession = SparkSession.builder()
    .appName(APP_NAME)
    .master("local")
    .config("spark.driver.host", "127.0.0.1")
    .getOrCreate()
}

object FirstClass extends App {

  val sc: SparkSession = SparkSessionInstancer.getLocal()

  // Read records & create a Spark DataFrame
  val continentDF: DataFrame = sc.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load("C:\\Users\\cicco\\Projects\\neo4j-spark-scala-j11\\src\\main\\resources\\country_codes.csv")

  // Creating nodes using the Neo4jDataFrame
  Neo4jDataFrame.createNodes(sc.sparkContext, continentDF, ("Continent", continentDF.columns.toSeq))
}
