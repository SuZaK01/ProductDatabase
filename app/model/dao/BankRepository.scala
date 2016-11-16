package model.dao

import model.Bank

import scala.concurrent.Future

/**
  * Created by lukasz on 13.11.16.
  */

trait BankRepository extends BankTable {
  this: DBComponent =>

  import driver.api

//  def create(bank: Bank): Future[Long] = db.run{ bankTableAutoInc += bank }
//
//  def update(bank: Bank):Future[Int] = db.run(bankTableQuery.filter(_.id === bank.id.get).update(bank))
//
//  def getById(id: Long) = ???
//
//  def getAll = ???
}

private[dao] trait BankTable {
  this: DBComponent =>

  import driver.api._

  val bankTableQuery = TableQuery[BankTable]

  protected def bankTableAutoInc = bankTableQuery returning bankTableQuery.map(_.id)


  private[BankTable] class BankTable(tag: Tag) extends Table[Bank](tag, "bank") {
    val id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    val name = column[String]("name")

    def * = (name, id.?) <> (Bank.tupled, Bank.unapply)
  }


}


