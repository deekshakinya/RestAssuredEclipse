package restassuredDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class restAssured1 {
	
	public static String baseURI="https://api.trello.com";
	
	@Test(enabled=false)
	public void testcase1(){
		RestAssured.baseURI=baseURI;
		given()
		.param("key","029d28e2dcc1f1f1692c8a0e4da3af9b")
		.param("token","ac676b45733898b5fd7e5b5ffc6ab24e341b2985b299e1b9899b073f0aee69ea")
		.when()
		.get("/1/boards/60c8581f548b5051a7f8071c")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.and()
		.body("name",equalTo("Deeksha1"))
		.and()
		.body("desc",equalTo("postman check"));
		System.out.print("Executed successfully");
		
	}
	

	@Test(enabled=false)
	public void testcase2(){
		RestAssured.baseURI=baseURI;
		given()
		.param("key","029d28e2dcc1f1f1692c8a0e4da3af9b")
		.param("token","ac676b45733898b5fd7e5b5ffc6ab24e341b2985b299e1b9899b073f0aee69ea")
		.when()
		.get("/1/boards/60c8581f548b5051a7f8071c")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.and()
		.header("Content-Type","application/json; charset=utf-8");
		System.out.print("Executed successfully");
	}
	
	
		@Test
		public void testcase3(){
			
			String boardName="deekshaNewBoard"+ (int) (Math.random()*100);
			RestAssured.baseURI=baseURI;
			given()
			.queryParam("key","029d28e2dcc1f1f1692c8a0e4da3af9b")
			.queryParam("token","ac676b45733898b5fd7e5b5ffc6ab24e341b2985b299e1b9899b073f0aee69ea")
			.queryParam("name",boardName)
			.header("Content-Type", "application/json; charset=utf-8")
			.when()
			.post("/1/boards")
			.then()
			.assertThat()
			.statusCode(200);
			System.out.print("Executed successfully");
		
	}

}
