import com.typesafe.config.ConfigFactory
import neo4j.Neo4jConnector
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.neo4j.spark.Neo4j
import org.neo4j.spark.dataframe.Neo4jDataFrame

object SparkContextInstancer {
  private val APP_NAME: String = "PHONE_COMPANY_EXERCISE"

  def getLocal: SparkContext = get("local", "127.0.0.1")

  def get(master: String, sparkDriverHost: String): SparkContext = new SparkContext(
    new SparkConf()
      .setAppName(APP_NAME)
      .setMaster(master)
      .set("spark.driver.host", sparkDriverHost)
  )
}

object SparkSessionInstancer {
  private val APP_NAME: String = "PHONE_COMPANY_EXERCISE"

  def getLocal: SparkSession = SparkSession.builder()
    .appName(APP_NAME)
    .master("local")
    .config("spark.driver.host", "127.0.0.1")
    .getOrCreate()
}

object FirstClass extends App {

  val config = ConfigFactory.load("application.conf").getConfig("com.ram.batch")

  val sc: SparkContext = SparkContextInstancer.getLocal
  val ss: SparkSession = SparkSessionInstancer.getLocal

  def readCSV(sc: SparkSession, path: String): DataFrame = sc.read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load(path)

  // Read records & create a Spark DataFrame
  val countryContinentDF: DataFrame = readCSV(ss, "C:\\Users\\cicco\\Projects\\neo4j-spark-scala-j11\\src\\main\\resources\\country_codes.csv")

  // Creating nodes using the Neo4jDataFrame
  Neo4jDataFrame.createNodes(ss.sparkContext, countryContinentDF, ("continent", countryContinentDF.columns.toSeq))
}
