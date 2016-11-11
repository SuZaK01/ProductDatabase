package model.dao

import javax.inject.Inject

import model.Customer
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import shapeless._
import slick.driver.JdbcProfile
import slick.lifted.{ProvenShape, TableQuery}
import slickless._


/**
  * Created by lukasz on 10.11.16.
  */
class CustomerDAO @Inject()(addressDAO: AddressDAO, protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  lazy val Customers = TableQuery[CustomerTable]

  import driver.api._

   def findById(id: Long) = db.run(Customers.filter(_.id === id).result)

   def findByLastName(name: String) = db.run(Customers.filter(_.lastName.toLowerCase.like(name.toLowerCase())).result)

   def findAll() = db.run(Customers.result)

  class CustomerTable(tag: Tag) extends Table[Customer](tag, "customers") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def firstName = column[String]("first_name")

    def lastName = column[String]("last_name")

    def properties1 = column[Option[String]]("properties1")

    def properties2 = column[Option[String]]("properties2")

    def properties3 = column[Option[String]]("properties3")

    def properties4 = column[Option[String]]("properties4")

    def properties5 = column[Option[String]]("properties5")

    def properties6 = column[Option[String]]("properties6")

    def properties7 = column[Option[String]]("properties7")

    def properties8 = column[Option[String]]("properties8")

    def properties9 = column[Option[String]]("properties9")

    def properties10 = column[Option[String]]("properties10")

    def properties11 = column[Option[String]]("properties11")

    def properties12 = column[Option[String]]("properties12")

    def properties13 = column[Option[String]]("properties13")

    def properties14 = column[Option[String]]("properties14")

    def properties15 = column[Option[String]]("properties15")

    def properties16 = column[Option[String]]("properties16")

    def properties17 = column[Option[String]]("properties17")

    def properties18 = column[Option[String]]("properties18")

    def properties19 = column[Option[String]]("properties19")

//    def properties20 = column[Option[String]]("properties20")
//
//    def properties21 = column[Option[String]]("properties21")
//
//    def properties22 = column[Option[String]]("properties22")
//
//    def properties23 = column[Option[String]]("properties23")
//
//    def properties24 = column[Option[String]]("properties24")
//
//    def properties25 = column[Option[String]]("properties25")
//
//    def properties26 = column[Option[String]]("properties26")


    def * : ProvenShape[Customer] = (
      id :: firstName
        :: lastName
        :: properties1
        :: properties2
        :: properties3
        :: properties4
        :: properties5
        :: properties6
        :: properties7
        :: properties8
        :: properties9
        :: properties10
        :: properties11
        :: properties12
        :: properties13
        :: properties14
        :: properties15
        :: properties16
        :: properties17
        :: properties18
        :: properties19
//        :: properties20
//        :: properties21
//        :: properties22
//        :: properties23
//        :: properties24
//        :: properties25
//        :: properties26
        :: HNil).mappedWith(Generic[Customer])
  }
}
