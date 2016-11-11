package controllers

import javax.inject._

import akka.stream.scaladsl.FileIO
import akka.util.ByteString
import play.api.http.HttpEntity
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport{

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  def index2 = Action { implicit req =>
    Ok(views.html.index("inne gadki"))
  }

  def hello(name: String) = Action { implicit  req =>
    Ok(s"Hello! $name")
  }

  def echo = Action {
    Result (
      header = ResponseHeader(217, Map.empty),
      body = HttpEntity.Strict(ByteString("Hello world!"), Some("text/plain"))
    )
  }

  def notfound = Action { Redirect("/", METHOD_NOT_ALLOWED) }

  val pageNotFound = NotFound(<h1>Page not found!</h1>)

  def check() = Action{
    Ok("OK")
  }

}
