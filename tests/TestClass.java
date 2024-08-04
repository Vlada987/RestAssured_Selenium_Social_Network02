package tests;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frontend.BrowserAction;
import io.restassured.response.Response;
import pojo.MyPost;
import rest.Context;
import rest.EContentType;
import rest.Methods;
import util.Data;
import util.UtilMethods;

public class TestClass {

	Context context = new Context();
	String postID = "";
	String mynewPost = "";
	String photoPostId = "";

@BeforeTest
	public void before() {

		context.baseURL = Data.baseUrl;
		context.requestHeaderParams.put("Authorization", "Bearer " + Data.pageToken);

	}

//Getting the page and assert name of the page	
@Test(priority=1, groups="backend")
	public void test01_getUser() {

		context.URI = "xxx";
		Response resp = Methods.GET(context);

		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertEquals(resp.jsonPath().get("name"), "xxx");

	}

//Getting all post on the page and check random post presence
@Test(priority=2, groups="backend")
	public void test02_getAllPosts() {

		context.URI = "xxxxxx";
		Response resp = Methods.GET(context);
		List<String> myPosts = UtilMethods.getDataAsList(resp, "data.message");

		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertTrue(myPosts.size() > 7);
		Assert.assertTrue(myPosts.stream().filter(p -> p != null).anyMatch(p -> p.equals("xxx")));

	}

//Create a new post on page
@Test(priority=3, groups="backend")
	public void test03_createPost() {

		context.URI = "xxxxxxxxxxxx";
		MyPost mypost = new MyPost();
		mynewPost = UtilMethods.createRandomMsg();
		mypost.setMessage(mynewPost);
		context.requestBody = new JSONObject(mypost);
		context.requestContentType = EContentType.JSON;
		Response resp = Methods.POST(context);
		postID = resp.jsonPath().get("id");

		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertTrue(postID.length() > 20);
	}

//Get the new created post and assert message
@Test(priority=4, groups="backend")
	public void test04_getCreatedPost() {

		context.URI = "/{post}";
		Map<String, Object> myMap = new HashMap<>();
		myMap.put("post", postID);
		context.pathParams = myMap;
		Response resp = Methods.GET(context);

		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertEquals(resp.jsonPath().get("message"), mynewPost);
		context.pathParams = new HashMap<>();
	}

//Post a photo on page
@Test(priority=5, groups="backend")
	public void test05_postPicture() {

		context.URI = "xxxxxxxx";
		context.multiParts.put("file", new File(Data.postPhotoPath + "\\photo1.png"));
		context.requestContentType = EContentType.FORM_DATA;
		Response resp = Methods.POST(context);
		photoPostId = resp.jsonPath().get("post_id");

		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertTrue(photoPostId.length() > 20);
	}

//Get the new created photo
@Test(priority=6, groups="backend")
	public void test06_getPicture() {

		context.URI = "/{photo}";
		Map<String, Object> myMap = new HashMap<>();
		myMap.put("photo", photoPostId);
		context.pathParams = myMap;
		Response resp = Methods.GET(context);

		Assert.assertEquals(resp.statusCode(), 200);
		Assert.assertTrue(resp.jsonPath().get("created_time").toString().contains("2024"));
	}
//Open the page with selenium find all post and find and assert last post
	@Test(priority = 7, groups = "frontend")
	public void test07_checkPostOnPage() throws InterruptedException {

		BrowserAction ba = new BrowserAction();
		List<String> myPosts = ba.getData();

		Assert.assertTrue(myPosts.stream().anyMatch(e -> e.equals(mynewPost)));
	}
//Open the page with selenium find the ID of last posted photo and assert IDs
	@Test(priority = 8, groups = "frontend")
	public void test08_checkphotoOnPAge() {

		BrowserAction ba = new BrowserAction();
		Assert.assertTrue(ba.actualPhotoID.equals(photoPostId));
	}

}
