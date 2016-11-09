package controllers

import javax.inject.Inject

import model.Product
import model.dao.ProductDAO
import play.api.data.{Form, Forms}
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller, Flash}

/**
  * Created by lukasz on 19.07.16.
  */
class ProductController @Inject()(productDAO: ProductDAO, val messagesApi: MessagesApi)
  extends Controller with I18nSupport {


  private val productForm: Form[Product] = Form(
    Forms.mapping(
      "ean" -> Forms.longNumber.verifying("Duplicate", Product.findByEan(_).isEmpty),
      "name" -> Forms.nonEmptyText,
      "desc" -> Forms.nonEmptyText
    )(Product.apply)(Product.unapply)
  )

  def listOfProds = Action.async { implicit req =>
    productDAO.all().map { case products => Ok(views.html.list(products)) }
  }

  def show(ean: Long) = Action.async { implicit req =>
    productDAO.findByEan(ean) map { case product =>
      Ok(views.html.products.details(product.head))
    }
  }

  def newProduct = Action { implicit req => {
    val form = if (req.flash.get("error").isDefined) productForm.bind(req.flash.data) else productForm
    Ok(views.html.products.editProduct(form))
  }
  }

  def save = Action { implicit req =>
    val newProductForm = productForm.bindFromRequest()

    newProductForm.fold(
      hasErrors = form => Redirect(routes.ProductController.newProduct()).flashing(Flash(form.data) + ("error" -> Messages("validation.error"))),
      success = { newProduct =>
        Product.add(newProduct)
        val message = Messages("product.new.success") + ": " + newProduct.name
        Redirect(routes.ProductController.show(newProduct.ean)).flashing("success" -> message)
      }
    )
  }
}

