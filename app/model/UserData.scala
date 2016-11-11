package model

/**
  * Created by lukasz on 11.11.16.
  */
case class UserData(id: Option[Long], email: String, firstName: Option[String], lastName: Option[String])

object UserData {

  def tupled = (UserData.apply _).tupled

}
