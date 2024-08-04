package rest;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

//import org.json.JSONObject;

public class Context {
	
	
	public IAuth auth;
	public String apikey; 
	public String apiSecret;
	public String token;
	public String tokenSecret;	
	public String baseURL;
	public EContentType requestContentType;
	public String URI;
	public JSONObject requestBody; 
	public String requestBodyString;
	public String reqHeader;
	public Map<String,Object>queryParams=new HashMap<>();
	public Map<String,Object>pathParams=new HashMap<>();
	public Map<String,Object>requestHeaderParams=new HashMap<>();
	public Map<String,Object>formParams=new HashMap<>();
	public Map<String,Object>formParam=new HashMap<>();
	public Map<String,Object>multiParts=new HashMap<>();	


}