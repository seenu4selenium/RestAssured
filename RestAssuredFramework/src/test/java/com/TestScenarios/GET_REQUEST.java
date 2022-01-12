package com.TestScenarios;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GET_REQUEST {

	public static void main(String[] args) {
		// Specify the base URL
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("responseBody code is:" + responseBody);
		// status validation code
		int statusCode = response.getStatusCode();
		System.out.println("Status code is : " + statusCode);
		
		//status line verification
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

	}

}
