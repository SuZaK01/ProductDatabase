package controllers

import javax.inject.Inject

import model.UserData
import model.dao.UserDAO
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller, Flash}


/**
  * Created by lukasz on 11.11.16.
  */
class UserController @Inject()(userDAO: UserDAO, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val userDataForm = Form(
    mapping(
      "id" -> ignored(None: Option[Long]),
      "email" -> email.verifying("Maximum length is 512", _.length <= 512),
      "firstName" -> optional(text(maxLength = 64)),
      "lastName" -> optional(text(maxLength = 64))
    )(UserData.apply)(UserData.unapply)
  )


  def userList() = Action.async { implicit req =>
    userDAO.findAll().map { users => Ok(views.html.users.users(users)(userDataForm)) }
  }

  def addUser = Action.async { implicit req =>
    userDataForm.bindFromRequest.fold(
      formWithErrors => {
        userDAO.findAll().map { users =>
          BadRequest(views.html.users.users(users)(formWithErrors))
        }
      },
      user => {
        userDAO.insertUser(user).map { user =>
          Redirect(routes.UserController.userList())
        } recoverWith {
          case _ => userDAO.findAll().map { users =>
            BadRequest(views.html.users.users(users)(userDataForm
              .withGlobalError(s"Duplicte email address: ${user.email}")))
          }
        }
      }
    )
  }

/*  def saveUser = Action.async{ implicit  req =>
    userDataForm.bindFromRequest.fold(
      hasErrors = form => Redirect(routes.UserController.editUser(form.value.get.id.get)),
      success = { user =>
        userDAO.updateUser(user).map( () =>
          Redirect(routes.UserController.editUser(user.id.getOrElse(0))))
      }
    )
  }*/

  def deleteUser(id: Long) = Action.async { implicit req =>
    userDAO.deleteUser(id).map { _ => Redirect(routes.UserController.userList()) }
  }

  def editUser(id: Long) = Action.async { implicit req =>
    userDAO.findUser(id).map { case Some(user) => {
      val filledForm = userDataForm.fill(user)
      Ok(views.html.users.editUser(user)(filledForm))
    }
    case None => NotFound
    }
  }

}
