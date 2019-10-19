package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers.{GET, contentAsString, contentType, route, status, stubControllerComponents}
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._


class ComplianceControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "ComplianceController GET" should {

    "should return all values" in {
      val controller = new ComplianceController(stubControllerComponents())
      val complianceType = "all"
      val complianceController = controller.index(complianceType).apply(FakeRequest(GET, "/compliance"))

      status(complianceController) mustBe OK
      contentType(complianceController) mustBe Some("application/json")
      contentAsString(complianceController) must include ("gdpr")
      contentAsString(complianceController) must include ("unprotected-devices")
      contentAsString(complianceController) must include ("uninspectable-data")
    }

    "should return gdpr value" in {
      val controller = new ComplianceController(stubControllerComponents())
      val complianceType = "gdpr"
      val complianceController = controller.index(complianceType).apply(FakeRequest(GET, "/compliance"))

      status(complianceController) mustBe OK
      contentType(complianceController) mustBe Some("application/json")
      contentAsString(complianceController) must include ("gdpr")
      contentAsString(complianceController) must not include ("unprotected-devices")
      contentAsString(complianceController) must not include ("uninspectable-data")
    }

    "should return unprotected-devices value" in {
      val controller = new ComplianceController(stubControllerComponents())
      val complianceType = "unprotected-devices"
      val complianceController = controller.index(complianceType).apply(FakeRequest(GET, "/compliance"))

      status(complianceController) mustBe OK
      contentType(complianceController) mustBe Some("application/json")
      contentAsString(complianceController) must not include ("gdpr")
      contentAsString(complianceController) must include ("unprotected-devices")
      contentAsString(complianceController) must not include ("uninspectable-data")
    }

    "should return uninspectable-data value" in {
      val controller = new ComplianceController(stubControllerComponents())
      val complianceType = "uninspectable-data"
      val complianceController = controller.index(complianceType).apply(FakeRequest(GET, "/compliance"))

      status(complianceController) mustBe OK
      contentType(complianceController) mustBe Some("application/json")
      contentAsString(complianceController) must not include ("gdpr")
      contentAsString(complianceController) must not include ("unprotected-devices")
      contentAsString(complianceController) must include ("uninspectable-data")
    }

    "should return error for unknown value" in {
      val controller = new ComplianceController(stubControllerComponents())
      val complianceType = "unknown"
      val complianceController = controller.index(complianceType).apply(FakeRequest(GET, "/compliance"))

      status(complianceController) mustBe  NOT_FOUND
      contentType(complianceController) mustBe Some("text/plain")
      contentAsString(complianceController) must include ("Type not found")
    }
  }
}
