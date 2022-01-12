package com.TestScenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_ValidateResponseStatusCodeAndStatusLine {
	@Test
	public void GetWeatherDetails() {
		SoftAssert sa = new SoftAssert();

		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get the status code from the Response. In case of a successfull interaction
		// with the web service,
		// we should get a status code of 200.
		int statusCode = response.getStatusCode();

		System.out.println("getStatusCode is " + response.getStatusCode());
		// Assert that correct status code is returned.
		// Assert.assertEquals(statusCode , 200 , "Correct status code returned");
		sa.assertEquals(statusCode, 200, "Correct status code returned");

		RestAssured.baseURI = "https://www.amazon.com";
		RequestSpecification httpRequest1 = RestAssured.given();
		Response response1 = httpRequest1.get("/error");
		int statusCode1 = response1.getStatusCode();
		System.out.println("error status code: " + response1.getStatusCode());
		// Assert.assertEquals(statusCode1 , 400 ,"Correct status code returned");
		sa.assertEquals(statusCode1, 400, "Correct status code returned");
	
		
		// How to Validate Response Status Line?

		// Get the status line from the Response and store it in a variable called
		// statusLine
		String statusLine = response.getStatusLine();
		System.out.println("StatusLine is " + response.getStatusLine());
		sa.assertEquals(statusLine, "HTTP/1.1 200 OK" /* expected value */, "Correct status code returned");

//		Assert.assertEquals(statusLine , "HTTP/1.1 200 OK" /* expected value */,
//				"Correct status code returned");

		// How to validate an Error Status Code?

		// RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";

		// To print the exception if any assertion failures
		sa.assertAll();
		
	}
}
