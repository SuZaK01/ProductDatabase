package controllers

import javax.inject.Inject

import model.Product
import model.dao.ProductDAO
import play.api.Logger
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

import play.api.libs.concurrent.Execution.Implicits.defaultContext


/**
  * Created by lukasz on 30.09.16.
  */
class ClientController @Inject()(productDAO: ProductDAO, val messagesApi: MessagesApi)
  extends Controller with I18nSupport {


  def show(id: Long) = Action { implicit request =>
    Ok(s"entered id: $id")
  }

  def showAllProducts() = Action.async { implicit req =>
    productDAO.findAll().map{ case products => Ok(views.html.index(products.toString())) }
  }

  def insertProducts() = Action.async { implicit req =>
    val product = Product(3423242342L, "NowyProduct", "Zjakimśopisem")
    Logger.debug(s"product created: ${product}")
    productDAO.insert(product).map(_ => Redirect(routes.ProductController.listOfProds()))
  }

//  def innyController() = Action.async{ implicit request =>
//    productDAO.all().filter(_.name === "cos").map{ Ok("jakiś napis coś niewiadomo przesyłający") }
//
//  }

  //  def index(name: String)  = Action.async{ implicit request =>
  //    val resultingUsers = dbConfig.db.run("select h2version() from dual")
  //    resultingUsers.map(users => Ok("test"))
  //    OK(" ")
  //  }

}
