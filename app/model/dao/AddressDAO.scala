package model.dao

import javax.inject.Inject

import model.Address
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.TableQuery

/**
  * Created by lukasz on 10.11.16.
  */
class AddressDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  lazy val Addresses = TableQuery[AddressTable]

  import driver.api._


  class AddressTable(tag: Tag) extends Table[Address](tag, "addresses") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def customerId = column[Long]("customer_id")

    def street = column[String]("street")

    def houseNr = column[String]("house_nr")

    def aprtmntNr = column[Option[String]]("apartament_nr")

    def zipCode = column[String]("zip")

    def city = column[String]("city")

    def country = column[Option[String]]("country")

    def * = (id, customerId, street, houseNr, aprtmntNr, zipCode, city, country) <>
      (Address.tupled, Address.unapply)
  }

}
