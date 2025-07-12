package com.TestScenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC011_DELETE {
	// PUT Request using Rest Assured
	// Use local JsonServer URL for POST operations

	@Test
	public void deleteEmpRecord() {

		//int empid = 15410;

		//RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
		RestAssured.baseURI= "http://localhost:3000/Employee/";
		
		RequestSpecification request = RestAssured.given();

		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");

		// Delete the request and check the response
		//Response response = request.delete("/delete/" + empid);
		Response response = request.delete("570d");

		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);

//		String jsonString = response.asString();
//		Assert.assertEquals(jsonString.contains("successfully! deleted Records"), true);
//		
		
		
		
	}
}
