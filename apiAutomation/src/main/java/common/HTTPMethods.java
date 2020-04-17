/**
 * @author prgupta
 */
package common;

import contextManager.AutomationContextManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HTTPMethods {

	private String generateURL(String EndPoint) {
		String Url = AutomationContextManager.getURL();
		String Protocol = AutomationContextManager.getProtocol();
		return Protocol+Url+EndPoint;
	}

	protected Response getCall(String HeaderName, Object ValueName, String EndPoint) {
		RestAssured.baseURI = generateURL(EndPoint);
		RequestSpecification request = RestAssured.given();
		request.header(HeaderName, ValueName);
		Response response = request.get();
		AutomationContextManager.getExtentTest().info("API Response ->"+ response.prettyPrint());
		return response;		
	}

	protected Response postCall(String HeaderName, String ValueName, String EndPoint, String RequestBody) {
		RestAssured.baseURI = generateURL(EndPoint);
		RequestSpecification request = RestAssured.given();
		request.header(HeaderName, ValueName);
		Response response = request.body(RequestBody).post();
		AutomationContextManager.getExtentTest().info("API Response ->"+ response.prettyPrint());
		return response;
	}

	protected Response deleteCall(String HeaderName, String ValueName, String endPoint, String ResponseID) {
		int respID = Integer.parseInt(ResponseID);
		AutomationContextManager.getExtentTest().info("Deleting ID ->"+ResponseID);
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		RequestSpecification request = RestAssured.given();
		request.header(HeaderName, ValueName);
		Response response = request.delete("/api/v1/delete/"+respID);
		AutomationContextManager.getExtentTest().info("API Response ->"+ response.prettyPrint());
		return response;
	}
}









