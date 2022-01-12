package com.TestScenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC006_ReadJSON_ResponseBodyUsingRestAssured {
	@Test
	public void WeatherMessageBody() {
		RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Retrieve the body of the Response
		ResponseBody body = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the body
		// into the string representation.
		System.out.println("Response Body is: " + body.asString());

		// output:
		// ==========
		// Response Body is: {"City":"Hyderabad","Temperature":"14 Degree
		// celsius","Humidity":"127 Percent","Weather Description":"scattered clouds",
		// "Wind Speed":"48 Km per hour","Wind Direction degree":"12 Degree"}

		// How to Validate Response Body contains some String?
		// To check for sub string presence get the Response body as a String.
		// Do a String.contains
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Hyderabad") /* Expected value */, true /* Actual Value */,
				"Response body contains Hyderabad");

		// How to Extract a Node text from Response using JsonPath?

		/*
		 * { "City": "Hyderabad", "Temperature": "25.51 Degree celsius", "Humidity":
		 * "94 Percent", "Weather Description": "mist", "Wind Speed": "1 Km per hour",
		 * "Wind Direction degree": " Degree" }
		 */
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("City");

		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);

		// Validate the response
		Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

		
		// Print the temperature node
		System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));

		// Print the humidity node
		System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));

		// Print weather description
		System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));

		// Print Wind Speed
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));

		// Print Wind Direction Degree
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));

	}
}
