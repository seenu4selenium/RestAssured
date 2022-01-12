package com.TestScenarios;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_ValidateResponseHeaderUsingRestAssured {
	//How to read different Header Types from HTTP Response?
//	Header Types from the Response:
//
//		Content-Type
//		Server
//		Content-Encoding
	
	@Test
	public void GetWeatherHeaders()
	{
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Reader header of a give name. In this line we will get Header named Content-Type
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);

		// Reader header of a give name. In this line we will get Header named Server
		String serverType =  response.header("Server");
		System.out.println("Server value: " + serverType);

		// Reader header of a give name. In this line we will get Header named Content-Encoding
		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);
	}
}
