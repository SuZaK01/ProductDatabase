package controllers

import javax.inject.Inject

import model.Customer
import model.dao.CustomerDAO
import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by lukasz on 09.11.16.
  */
class CustomerController @Inject()(customerDAO: CustomerDAO, val messagesApi: MessagesApi)
  extends Controller with I18nSupport {

  val NewCustomerForm = Form(
    mapping(
      "id"->longNumber,
      "firstName"-> nonEmptyText,
      "lastName"-> nonEmptyText,
      "properties1"-> optional(text),
      "properties2"-> optional(text),
      "properties3"-> optional(text),
      "properties4"-> optional(text),
      "properties5"-> optional(text),
      "properties6"-> optional(text),
      "properties7"-> optional(text),
      "properties8"-> optional(text),
      "properties9"-> optional(text),
      "properties10"-> optional(text),
      "properties11"-> optional(text),
      "properties12"-> optional(text),
      "properties13"-> optional(text),
      "properties14"-> optional(text),
      "properties15"-> optional(text),
      "properties16"-> optional(text),
      "properties17"-> optional(text),
      "properties18"-> optional(text),
      "properties19"-> optional(text)
    )(Customer.apply)(Customer.unapply)

  )

  def showAll = Action.async { implicit req =>
    customerDAO.findAll().map { customers => {
      Logger.debug(customers.toString())
      Ok(views.html.customers.customerList(customers))
    }
    }

  }

}
