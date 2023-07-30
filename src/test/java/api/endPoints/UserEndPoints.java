package api.endPoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// CRUD implementation  Create, Retrieve, Update, Delete

public class UserEndPoints {
	
	public static Response createUser(User payload){
		Response responce = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.post_url);
		return responce;
	}
	
	public static Response getUser(String userName) {
		Response response = given()
					.pathParam("username", userName)
				.when()
					.get(Routes.get_url);
		return response;
	}
	
	public static Response updateUser(String userName,User payload) {
		Response response = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username",userName)
					.body(payload)
				.when()
					.put(Routes.put_url);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response = given()
					.pathParam("username", userName)
				.when()
					.delete(Routes.delete_url);
		return response; 
	}
	

}
