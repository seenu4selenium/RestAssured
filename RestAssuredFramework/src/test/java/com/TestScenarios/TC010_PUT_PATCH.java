package com.TestScenarios;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;


public class TC010_PUT_PATCH {
	//PUT Request using Rest Assured
	// Use local JsonServer URL for POST operations

	
	@Test
	public void UpdateRecords(){
			int empid = 15410;

			//RestAssured.baseURI ="https://dummy.restapiexample.com/api/v1";
			RestAssured.baseURI= "http://localhost:3000/Employee/";
			RequestSpecification request = RestAssured.given();
			
			JSONObject requestParams = new JSONObject();
			requestParams.put("FirstName", "Ashok Gowd"); 
			

			request.body(requestParams.toJSONString());
			//Response response = request.put("/update/"+ empid);
			//Response response = request.put("ed52");
			Response response = request.patch("222d");

			int statusCode = response.getStatusCode();
			System.out.println(response.asString());
			Assert.assertEquals(statusCode, 200); 

		}
}
