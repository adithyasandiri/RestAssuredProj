package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endPoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDriventest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPost(String UserID, String UserName, String FirstName, String LastName, String email, String Pwd,
			String phone) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(email);
		userPayload.setPassword(Pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(userPayload);
		
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
	
	@Test(priority = 2,dataProvider="UserNames",dataProviderClass= DataProviders.class)
	public void testDeleteUserByNames(String userName) {
		
		Response response = UserEndPoints.deleteUser(userName);
		//response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
