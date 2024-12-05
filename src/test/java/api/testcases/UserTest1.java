package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint2;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UserTest1 {
	Faker faker;
	User userPayload;
	public static Logger logger;
	@BeforeClass
	public void generateTestData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	   //obtain logger
		logger = LogManager.getLogger("RestAssuredFramework");
		



	}

	@Test(priority = 1)
	public void testCreateUser() {
		Response response = UserEndPoint2.createUser(userPayload);
	//log response 
		response.then().log().all();
	//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("create user executed");
	}


	@Test(priority = 2)
	public void testGetUserData() {
		Response response = UserEndPoint2.getUser(this.userPayload.getUsername());
	//log response 
		System.out.println("Read user data"     );
		response.then().log().all();
	//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	logger.info("get user data");

	}

	@Test(priority = 3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		Response response = UserEndPoint2.updateUser(this.userPayload.getUsername(), userPayload);
	//log response 
		System.out.println("Update user data");
		response.then().log().all();
	//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	//Read first name is updated or not
		Response responsePostUpdate = UserEndPoint2.getUser(this.userPayload.getUsername());
		System.out.println("Update user data");
		responsePostUpdate.then().log().all();
	     logger.info("Update user");

	}

	@Test(priority = 4)
	public void testDeleteUser() {
		
		Response response = UserEndPoint2.deleteUser(this.userPayload.getUsername());
	//log response 
		System.out.println("Delete user data");
		response.then().log().all();
	//validation
		Assert.assertEquals(response.getStatusCode(), 200);
	    logger.info("Delete useer");

	}}



