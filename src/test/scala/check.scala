import MC_MSFT.{conf, diff, flr, next, rnd, spark}
import com.typesafe.config.{Config, ConfigFactory}
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test


object check{
  val Config: Config = ConfigFactory.load("application.conf")

  val input = spark.read.format("csv")
    .option("header","true")
    .option("mode", "DROPMALFORMED")
    .load(Config.getString("stocks.MSFT"))

  class check {
    @Test def checkConfig(): Unit = {
      val cc = new check
      assertNotNull(Config)
    }

    @Test def checkVal(): Unit = {
      val st_num = 10
      assertEquals(st_num, Config.getInt("stocks.stock_num"))
    }

    @Test def checkAddress(): Unit = {
      val add = "src/main/resources/daily_MSFT.csv"
      assertEquals(add, Config.getString("stocks.MSFT"))
    }

    @Test def checkOutput(): Unit = {
      val out = "MSFT"
      assertEquals(out, Config.getString("MSFT"))
    }

    @Test def checkSize(): Unit = {
      val size = input.count().toInt
      assertEquals(size, 100)
    }
  }

}
