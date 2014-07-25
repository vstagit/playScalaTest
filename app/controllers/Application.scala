package controllers

import models.Member
import play.api.libs.json.{JsValue, JsObject}
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.membersDetail)
  }

  def membersDetail = Action {
    Ok(views.html.member(Member.getAll, inputForm))
  }

  def memberAction = Action { implicit request =>
    inputForm.bindFromRequest.fold(
      errors => BadRequest("Data not found"),
      x => x match {
        case (name, email) => {
          request.body.asFormUrlEncoded.map { map =>
            val actBtn = map.get("member").get(0)
            if (actBtn.equals("Add"))
              Member.add(name, email)
            if (actBtn.equals("Remove"))
              Member.remove(name, email)
          }
          Redirect(routes.Application.membersDetail)
        }
      })
  }

  val inputForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "email" -> nonEmptyText))
}