/**@author prgupta
 * 
 */
package apiHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;

import common.JsonParser;
import constants.TestSuiteConstants.RequestBodyAndResponseData;
import contextManager.AutomationContextManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class TestCaseHelperFile  {

	private static String responseId;

	public static String getResponseId() {
		return responseId;
	}

	public static void setResponseId(String id) {
		responseId = id;
	}

	public static String generateRequestBody() {
		HashMap<String, String> request = new HashMap<String, String>();
		request.put(RequestBodyAndResponseData.NameKey, RequestBodyAndResponseData.NameValue); 
		request.put(RequestBodyAndResponseData.SalaryKey, RequestBodyAndResponseData.SalaryValue); 
		request.put(RequestBodyAndResponseData.AgeKey, RequestBodyAndResponseData.AgeValue);
		JsonParser j = new JsonParser();
		String requestBody = j.jsonBodyCreation(request);
		AutomationContextManager.getExtentTest().info("POST call Request Body ->"+requestBody);
		return requestBody;
	}

	public static void validateGetCallResponse(Response response, List<Map<String, String>> expecetedData) {
		AutomationContextManager.getExtentTest().info("StatusCode of API Response ->"+response.getStatusCode());
		JsonPath jsonPathEvaluator = response.jsonPath();
		String status = jsonPathEvaluator.get(RequestBodyAndResponseData.StatusKey);	
		Assert.assertTrue(status.equalsIgnoreCase(RequestBodyAndResponseData.StatusValue));
		List<Map<String, String>> actualData = jsonPathEvaluator.getJsonObject(RequestBodyAndResponseData.DataKey);
		for (int i =0;i<expecetedData.size();i++) {
			Set<String> Expkeys = expecetedData.get(i).keySet();
			Expkeys.remove("id");
			for(String key: Expkeys) {
				AutomationContextManager.getExtentTest().info("Key ->"+key+" : Actual Value -> "+actualData.get(i).get(key) +" Expected Value -> "+expecetedData.get(i).get(key));
				Assert.assertEquals(actualData.get(i).get(key), expecetedData.get(i).get(key));
			}
		}
	}

	public static void validatePostCallResponse(Response response, Map expectedData) {
		AutomationContextManager.getExtentTest().info("StatusCode of API Response ->"+response.getStatusCode());
		JsonPath jsonPathEvaluator = response.jsonPath();
		String status = jsonPathEvaluator.get(RequestBodyAndResponseData.StatusKey);	
		Assert.assertTrue(status.equalsIgnoreCase(RequestBodyAndResponseData.StatusValue));
		Map<String, Object> actualData = jsonPathEvaluator.getMap(RequestBodyAndResponseData.DataKey);
		DeleteCallManager.setIdToBeDeleted((int) actualData.get("id"));
		Set<String> Expkeys = expectedData.keySet();	
		for(String key: Expkeys) {
			AutomationContextManager.getExtentTest().info("Key -> "+key+" : Actual Value -> "+actualData.get(key)+" Expected Value -> "+expectedData.get(key));
			Assert.assertEquals(actualData.get(key), expectedData.get(key));
		} 
	}

	public static void validateDeleteCallResponse(Response response) {
		AutomationContextManager.getExtentTest().info("StatusCode of API Response ->"+response.getStatusCode());
		JsonPath jsonPathEvaluator = response.jsonPath();
		String actualStatus = jsonPathEvaluator.get(RequestBodyAndResponseData.StatusKey);
		String actualMessage = jsonPathEvaluator.get(RequestBodyAndResponseData.MessageKey);
		Assert.assertEquals(actualStatus, RequestBodyAndResponseData.StatusValue);
		Assert.assertEquals(actualMessage, RequestBodyAndResponseData.SuccessMessageValue);
		AutomationContextManager.getExtentTest().info("Actual Response ->"+actualMessage+" Expeceted Response ->"+RequestBodyAndResponseData.SuccessMessageValue);
	}	
}
