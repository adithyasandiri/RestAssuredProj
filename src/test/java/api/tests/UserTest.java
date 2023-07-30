package api.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	User userPayload;
	@BeforeClass
	public void setupData() {
		faker  = new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
			
	}

	@Test(priority = 1)
	public void testPost() {
		Reporter.log("*************creating a user***********",true);
		
		Response response = UserEndPoints.createUser(userPayload);
		response.then().statusCode(200)
		.log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Reporter.log("************* user is created***********",true);
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		Reporter.log("*************Retrivening the user by name***********",true);
		
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().statusCode(200)
		.log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		Reporter.log("*************user Retrived***********",true);
	}
	@Test(priority = 3)
	public void testUpdateUserByUsername() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Reporter.log("*************Updating the user***********",true);
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
	
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = UserEndPoints.getUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		
		Reporter.log("*************User is Updated***********",true);
	}
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Reporter.log("*************Deleting a user***********",true);
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		
		Reporter.log("*************User is Deleted ***********",true);
	}
	
}
