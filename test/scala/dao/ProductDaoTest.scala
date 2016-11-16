package scala.dao

import model.dao.ProductDAO
import org.scalatest.BeforeAndAfterEach
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.db.DBApi
import play.api.db.evolutions.Evolutions
import play.api.inject.guice.GuiceApplicationBuilder

import scala.reflect.ClassTag

/**
  * Created by lukasz on 13.11.16.
  */


class ProductDaoTest extends PlaySpec with OneAppPerTest with BeforeAndAfterEach {
  val productRepo = Injector.inject[ProductDAO]


  /*  override def afterEach(): Unit = EvolutionHelper.clean()

    "An item " should {
      "be inserted during the first test case" in new WithApplication(FakeApplication()) {
        val action = productRepo.insert(new Product(1, "A", "B"))
          .flatMap(_ => productRepo.findAll())
        val result = Await.result(action, Duration.Inf)

        result mustBe List(Product(1, "A", "B"))
      }
    }*/

}


object Injector {
  lazy val injector = (new GuiceApplicationBuilder).injector()

  def inject[T: ClassTag]: T = injector.instanceOf[T]
}

object EvolutionHelper {
  def clean() = {
    val dbapi = Injector.inject[DBApi]
    Evolutions.cleanupEvolutions(dbapi.database("default"))
  }
}