package restassuredDemo;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ECommerceRestTesting {
	public static String baseURI="https://ecommerceservice.herokuapp.com";
	public String accessToken;
	public String id;
	
	@Test(enabled=false)
	public void signUp(){
		RestAssured.baseURI=baseURI;
		
		String RequestBody="{\r\n" + 
				"	\"email\": \"deesh1@gmail.com\",\r\n" + 
				"	\"password\": \"kiny@1234\"\r\n" + 
				"}\r\n";
		Response response=given()
		.header("Content-Type","application/json")
		.body(RequestBody)
		
		.when()
		.post("/user/signup")
		
		.then()
		.assertThat()
		.statusCode(201)
		.and()
		.contentType(ContentType.JSON)
		.extract().response();
		System.out.println("Response:"+response.asString());
	}
	
	@Test(priority =0)
	public void login(){
		RestAssured.baseURI=baseURI;
		
		String RequestBody="{\r\n" + 
				"	\"email\": \"deesh1@gmail.com\",\r\n" + 
				"	\"password\": \"kiny@1234\"\r\n" + 
				"}\r\n";
		Response response=given()
		.header("Content-Type","application/json")
		.body(RequestBody)
		
		.when()
		.post("/user/login")
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.extract().response();
		String jsonResonseBody=response.asString();
		
		System.out.println("Response:"+response.asString());
		
		JsonPath responseBody=new JsonPath(jsonResonseBody);
		accessToken=responseBody.get("accessToken");
	}
	
	@Test(priority=1)
	public void getAllUser(){
		RestAssured.baseURI=baseURI;
		
		
		Response response=given()
		.header("Content-Type","application/json")
		.header("Authorization","bearer "+accessToken)
				
		.when()
		.get("/user")
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
		String jsonResonseBody=response.asString();
		
		System.out.println("Response:"+response.asString());
		
		JsonPath responseBody=new JsonPath(jsonResonseBody);
		
		id=responseBody.get("users[2]._id");
		System.out.println("Id:"+id);
	}

	
	@Test(priority=2)
	public void deleteUser(){             
		RestAssured.baseURI=baseURI;
		
		
		Response response=given()
		.header("Content-Type","application/json")
		.header("Authorization","bearer "+accessToken)
		//                 
		.when()
		.delete("/user/" +id)
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
		String jsonResonseBody=response.asString();
		
		System.out.println("Response:"+response.asString());
		
		JsonPath responseBody=new JsonPath(jsonResonseBody);
		
		String message=responseBody.get("Message");
		System.out.println(message);
	}

}
