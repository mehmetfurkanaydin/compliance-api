package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.{ Json, JsValue }

@Singleton
class ComplianceController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def prepareResponse(): JsValue = {
    val r = scala.util.Random
    val json: JsValue = Json.obj(
      "compliance" -> Json.arr(
        Json.obj(
           "gdpr"-> "%.2f".format(r.nextFloat).toDouble,
          "unprotected-devices"-> "%.2f".format(r.nextFloat).toDouble,
          "uninspectable-data"-> "%.2f".format(r.nextFloat).toDouble
        )
      )
    )
    json
  }

  def prepareResponse(complianceType: String): JsValue = {
    val r = scala.util.Random
    val json: JsValue = Json.obj(
      "compliance" -> Json.arr(
        Json.obj(
          complianceType-> "%.2f".format(r.nextFloat).toDouble
        )
      )
    )
    json
  }

  def index(complianceType: String) = Action { implicit request: Request[AnyContent] =>
    complianceType match {
      case "all" => Ok(prepareResponse())
      case "gdpr" => Ok(prepareResponse("gdpr"))
      case "unprotected-devices" => Ok(prepareResponse("unprotected-devices"))
      case "uninspectable-data" => Ok(prepareResponse("uninspectable-data"))
      case _ =>  NotFound("Type not found")
    }
  }
}
