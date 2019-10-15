package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class ComplianceController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok("compliance-Done")
  }
}
