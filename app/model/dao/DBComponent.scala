package model.dao

import slick.driver.JdbcProfile

/**
  * Created by lukasz on 13.11.16.
  */
trait DBComponent {
  val driver: JdbcProfile

  import driver.api._

  val db: Database

}
