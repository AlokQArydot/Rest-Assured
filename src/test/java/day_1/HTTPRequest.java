package day_1;

import  org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * 
given()  
        content Type , set cookies ,Add param, add auth, set Header info etc....
when()
        get, put, post, Delete 
then()
        Validate Source Code, extract Response, Extract Header Cookies  & Response Body 
*/



public class HTTPRequest {
	int id;
	
	@Test(priority=1)
	void getUser()
	{
		given()
		
		.when()
		   .get("https://reqres.in/api/users?page=2")
		
		.then()
		    .statusCode(200)
		    .body("page",equalTo(2))
		    .log().all();
		    
	}
	@Test(priority=1)
	void CreateUser() {
		
		HashMap data= new HashMap();
		data.put("name", "alok");
		data.put("job", "training");
		
		id=given()
		     .contentType("application/json")
		     .body(data)
		.when()
		     .post("https://reqres.in/api/users")
		 	 .jsonPath().getInt("id")
		 	 ;
		
		     /*.then()
		      		.statusCode(201)
		      		.log().all();*/
	}
	@Test(priority = 3,dependsOnMethods = ("CreateUser")) 
	void updateUser() {
		
		HashMap data= new HashMap();
		data.put("name", "Amar");
		data.put("job", "Developer");
		
		given()
		     .contentType("application/json")
		     .body(data)
		.when()
		     .put("https://reqres.in/api/users/"+id)
		     
		.then()
		     .statusCode(200)
		     .log().all();
	}
	@Test(priority = 4)
	void DeleteUser() 
	{
		given()
		
		.when()
			 .delete("https://reqres.in/api/users/"+id)
			 
		.then()
			 .statusCode(204)
			 .log().all();
	}

}

