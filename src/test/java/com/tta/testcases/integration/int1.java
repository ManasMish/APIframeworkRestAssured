package com.tta.testcases.integration;

import com.tta.base.BaseTest;
import com.tta.endpoints.APIConstants;
import com.tta.pojos.AuthRes;
import com.tta.pojos.Booking;
import com.tta.pojos.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.Assert;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class int1 extends BaseTest {
   @Test(groups = "integration",priority = 1)
   @Owner("Manas")
   @Description("POST Request for creating new Booking")
   public void createbookingint1(ITestContext iTestContext){
       requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
       response= RestAssured.given().spec(requestSpecification).when().body(payloadManager.createPayloadGSON()).post();
       validatableResponse=response.then().log().all();
       BookingRespons bookingRespons= payloadManager.bookingresponseJava(response.asString());
       assertThat(bookingRespons.getBookingid()).isNotNull();
       iTestContext.setAttribute("bookingid",bookingRespons.getBookingid());
   }
   @Test(groups="integration",priority = 2)
   @Owner("Manas")
   @Description("Creating a token for basic authentication")
   public void gettoken(ITestContext iTestContext){
       requestSpecification.basePath(APIConstants.auth_URL);
       response=RestAssured.given().spec(requestSpecification).when().body(payloadManager.setAuthpayload()).post();
       validatableResponse=response.then().log().all();
       String  authRes=payloadManager.gettokenfromJSON(response.asString());
       iTestContext.setAttribute("token",authRes);
   }
   @Test(groups = "integration",priority = 3)
    @Owner("Manas")
    @Description("Get Request for created booking")
    public void getbookingbyIDint1(ITestContext iTestContext){
       Integer bookigID=(Integer) iTestContext.getAttribute("bookingid");
       requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookigID);
       response=RestAssured.given().spec(requestSpecification).when().get();
       validatableResponse=response.then().log().all();
       Booking bookinggetreq=payloadManager.bookingresponsePUTreqJava(response.asString());
       assertThat(bookinggetreq.getFirstname()).isNotNull();
   }
   @Test(groups="integration",priority = 4)
    @Owner("Manas")
    @Description("PUT Request for created booking")
    public void putReqint1(ITestContext iTestContext){
       Integer bookingID=(Integer) iTestContext.getAttribute("bookingid");
       String token= (String) iTestContext.getAttribute("token");
       requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID);
       response=RestAssured.given().spec(requestSpecification).cookie("token",token).when().body(payloadManager.updatePayload()).put();
       validatableResponse=response.then().log().all();
       Booking putReqres=payloadManager.bookingresponsePUTreqJava(response.asString());
       assertThat(putReqres.getFirstname()).isEqualTo("Manas");
   }
   @Test(groups = "integration",priority = 5)
    @Owner("Manas")
    @Description("Delete Request for created booking")
    public void delReqint1(ITestContext iTestContext){
       Integer bookingID= (Integer) iTestContext.getAttribute("bookingid");
       String token= (String)iTestContext.getAttribute("token");
       requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID);
       response=RestAssured.given().spec(requestSpecification).cookie("token",token).when().delete();
       validatableResponse=response.then().log().all();
       validatableResponse.statusCode(201);
   }
}
