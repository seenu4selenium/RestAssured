package com.TestScenarios;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC009_POST_RequestUsingRestAssured {
	// Use local JsonServer URL for POST operations

	@Test
	public class RegistrationSuccessResponse {

		// Variable where value of SuccessCode node
		// will be copied
		// Note: The name should be exactly as the node name is
		// present in the Json
		public String SuccessCode;

		// Variable where value of Message node will
		// be copied
		// Note: The name should be exactly as the node name is
		// present in the Json
		public String Message;
	}

	@Test
	public class RegistrationFailureResponse {

		String FaultId;
		String fault;
	}

	@Test
	public void RegistrationSuccessful() {
		// Use local JsonServer URL for POST operations
		 RestAssured.baseURI = "https://demoqa.com/customer/register";
		//RestAssured.baseURI = "http://localhost:3000";

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender1");
		requestParams.put("LastName", "Singh1");
		requestParams.put("UserName", "63userf2d3d20111");
		requestParams.put("Password", "password11");
		requestParams.put("Email", "ed26dff39@gmail.com1");

		request.body(requestParams.toJSONString());
		 Response response = request.post("/register");
		//Response response = request.post("/Persons");

		ResponseBody body = response.getBody();
		System.out.println(response.body().asString());

		System.out.println("response.statusCode()" + response.statusCode());
//		if (response.statusCode() == 200) {
//			// Deserialize the Response body into RegistrationFailureResponse
//			RegistrationFailureResponse responseBody = body.as(RegistrationFailureResponse.class);
//
//			// Use the RegistrationFailureResponse class instance to Assert the values of
//			// Response.
//			System.out.println("responseBody.FaultId" + responseBody.FaultId);
//			System.out.println("responseBody.fault" + responseBody.fault);
//			Assert.assertEquals("User already exists", responseBody.FaultId);
//			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
//		} else if (response.statusCode() == 201) {
//			// Deserialize the Response body into RegistrationSuccessResponse
//			RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
//			// Use the RegistrationSuccessResponse class instance to Assert the values of
//			// Response.
//			System.out.println("responseBody.SuccessCode" + responseBody.SuccessCode);
//			System.out.println(" responseBody.Message" + responseBody.Message);
//			Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
//			Assert.assertEquals("Operation completed successfully", responseBody.Message);
//		}
	}

	// @Test
	public void RegistrationSuccessful01() {

		RestAssured.baseURI = "https://demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender");
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");
		requestParams.put("Email", "sample2ee26d9@gmail.com");

		request.body(requestParams.toJSONString());
		Response response = request.get("/register");

		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);

		System.out.println("Response body: " + response.body().asString());

	}
}
