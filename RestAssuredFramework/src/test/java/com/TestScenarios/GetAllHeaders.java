package com.TestScenarios;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllHeaders {
	// How to Print all the Headers from HTTP Response?
	// All the headers in a Response can also be printed by simply iterating over
	// each Header.
	// Response interface provides two methods
	// headers() : returns Headers
	// getHeaders() : returns Headers
    // This collection is represented by a class called io.restassured.http.Headers. 
	// Headers class implements the Iterable interface. Hence, for each (for( : ))
	// loop can be used to read all the headers, as shown in the code below:
	@Test
	public void IteratingOverHeaders() {
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";

		// RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get all the headers. Return value is of type Headers.
		// Headers class implements Iterable interface, hence we
		// can apply an advance for loop to go through all Headers
		// as shown in the code below
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
	}
	
	
	//How to Validate Response Header using Rest Assured?
	//Now that we have a mechanism to read a Header. Let's write a test to validate the values of the header by putting an Assert. 
	//The code is simple and its mostly same as the above code. The only difference is that instead of having a print statement,
	//TestNg Assert is used. Here is the code.
	
	@Test
	public void GetWeatherHeaders()
	{
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Reader header of a give name. In this line we will get
		// Header named Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType =  response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "nginx/1.12.1" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);
	}
	
	
	
	
	
}