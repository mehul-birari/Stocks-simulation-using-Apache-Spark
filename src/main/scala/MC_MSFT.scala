import java.util.{ArrayList, List, Random}
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.JavaConverters._
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, floor, lead, max, min, rand}
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import scala.collection.mutable.ListBuffer

//WVOIEQI8RE4BGXXR


//spark-submit --class MC --master yarn --deploy-mode client ./ ...jar
object MC_MSFT {

  import com.typesafe.config.ConfigFactory
  import org.slf4j.Logger
  import org.slf4j.LoggerFactory

  val log: Logger = LoggerFactory.getLogger(classOf[Nothing])
  val conf: Config = ConfigFactory.parseResources("application.conf") // Configuration file for inputs
  val spark = SparkSession.builder
    .master("local")
    .appName("Create RDD")
    .getOrCreate                        // sparksession variable

  val window = Window.orderBy("timestamp")      //for calculating the divide values using offset
  val next = lead("open", offset = 1).over(window)
  val diff = (col("open")/col("diff"))    //division of next price and current price
  val rnd = rand(100)*100               // for random number generation
  val flr = floor(col("Randomn"))     // for calculating the greatest integer lower than the value
  val profits = ListBuffer[Double]()          //for storing the calculated profits value
  val input = spark.read.format("csv")          //the input dataframe in csv format
    .option("header","true")
    .option("mode", "DROPMALFORMED")
    .load(conf.getString("stocks.MSFT"))
    .drop("high", "low", "close", "volume")
    .withColumn("diff", next)
    .withColumn("change", diff)
    .drop("diff")
    .withColumn("Randomn", rnd)
    .withColumn("Random", flr)
    .drop("Randomn")

  val Row(minV: String, maxV: String) = input.agg(min("open"), max("open")).head
  //for calculating the minimum and maximum value of price range
  var count: Int = 0
  var cnt = 0
  val dataset_count = input.count().toInt
  val prices = arrangeinDf(spark)       //generate dataframe from predicted prices

  def main(args: Array[String]) : Unit = {

    input.show()
    input.printSchema()
    input.agg(min("open"), max("open")).show

    prices.show()     //print the prices dataframe
  }

  //making decision randomly for buying or selling and generating profits accordingly
  def buyorsell(prices: ListBuffer[Double], stocks: Int): Double = {
    var profit : Double = 0
    var buy : Double = 0
    var sell : Double = 0
    var i = 0
    while ({
      i < dataset_count
    }) {
      if(i % 5 == 0) {
        sell += prices(i)
      }
      if(i % 9 == 0) {
        buy += prices(i)
      }
      i += 1; i-1;
    }
    profit = sell - buy
    profit
  }

  def collectLists() : ListBuffer[ListBuffer[Double]]={
    val l = ListBuffer[ListBuffer[Double]]()
    val rndn = new Random
    var i = 0
    while ({
        i < 1
    })
    {
      var j = 0
      while ({
        j < dataset_count
      })
      {
        l+=(new ListBuffer[Double])
        val r = rndn.nextDouble * (maxV.toDouble - minV.toDouble) + minV.toDouble
        l(i) += r

        { j += 1; j - 1 }
      }
      { i += 1; i - 1 }
    }

    //
    /*
    i = 0

    while ({
      i < 1
    })
    {
      var j = 0
      while ({
        j < dataset_count
      })
      {
        System.out.print(l(i)(j)+" i= " + i + " j= "+j+" ")

        { j += 1; j - 1 }
      }
      { i += 1; i - 1 }
      System.out.println("Count = "+ cnt)
      cnt = cnt + 1
    }
    */
    profits += buyorsell(l(0), conf.getInt("stocks.stock_num"))     //calculating the profits
    l
  }

  def arrangeinDf(sparkSession: SparkSession):Dataset[ListBuffer[ListBuffer[Double]]] = {
    import sparkSession.implicits._
    count = dataset_count
    (0 to count).map(i=>collectLists()).toDS()
  }

  import spark.implicits._
  val profitsDF = profits.toDF
  profitsDF.select("value").write.format("csv").save(conf.getString("output.out_MSFT"))

}