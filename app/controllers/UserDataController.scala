package controllers

import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller

/**
  * Created by lukasz on 10.11.16.
  */
class UserDataController extends Controller {

  case class UserData(name: String, age: Int)

  val userForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    )(UserData.apply)(UserData.unapply)
  )


}
