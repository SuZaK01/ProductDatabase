package model.dao

import javax.inject.Inject

import model.UserData
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import slick.lifted.{ProvenShape, TableQuery}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by lukasz on 11.11.16.
  */
class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  lazy val Users = TableQuery[UserTable]

  import driver.api._

  class UserTable(tag: Tag) extends Table[UserData](tag, "users") {
    def id = column[Option[Long]]("id", O.AutoInc, O.PrimaryKey)

    def email = column[String]("email")

    def firstName = column[Option[String]]("first_name")

    def lastName = column[Option[String]]("last_name")

    override def * : ProvenShape[UserData] = (id, email, firstName, lastName) <>
      (UserData.tupled, UserData.unapply)
  }

  def findAll(): Future[Seq[UserData]] = db.run(Users.sortBy(_.email).result)

  def insertUser(user: UserData) = db.run(Users += user).map { _ => () }

  def deleteUser(id: Long) = db.run(Users.filter(_.id === id).delete)

  def findUser(id: Long) = db.run(Users.filter(_.id === id).result.headOption)

  def updateUser(user: UserData): Future[Int] = db.run(Users
    .filter(_.id === user.id)
    .update(user))
}
