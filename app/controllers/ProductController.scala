package controllers

import javax.inject.Inject

import model.Product
import play.api.data.{Form, Forms}
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

/**
  * Created by lukasz on 19.07.16.
  */


class ProductController @Inject()(val messagesApi: MessagesApi)
  extends Controller with I18nSupport{

  private val productForm: Form[Product] = Form(
    Forms.mapping(
      "ean" -> Forms.longNumber.verifying("Duplicate", Product.findByEan(_).isEmpty),
      "name" -> Forms.nonEmptyText,
      "desc" -> Forms.nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  def listOfProds = Action { implicit req =>
    val list = Product.findAll
    Ok(views.html.list(list))
  }

  def show(ean: Long) = Action { implicit req =>
    Product.findByEan(ean) map { product =>
      Ok(views.html.products.details(product))
    } getOrElse (NotFound)
  }

}
