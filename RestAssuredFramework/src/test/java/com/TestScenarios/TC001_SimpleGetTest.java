package com.TestScenarios;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_SimpleGetTest {

	@Test
	public void GetWeatherDetails()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		//RestAssured.baseURI = "http://localhost:3000";

		
		// Get the RequestSpecification of the request that you want to sent to the server.
		//The server is specified by the BaseURI that we have specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		//Response response = httpRequest.request(Method.GET, "/Persons");

		

		// Now let us print the body of the message to see what response we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		//Response Body is =>  {"City":"Hyderabad","Temperature":"59 Degree celsius","Humidity":"76 Percent","Weather Description":"scattered clouds","Wind Speed":"143 Km per hour","Wind Direction degree":"171 Degree"}


	}

}