/**
 * @author prgupta
 */
package testData;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import constants.TestSuiteConstants.RequestBodyAndResponseData;

public class ExpectedTestData {

	public static Map expectedPostResponse () {
		HashMap<String, String> expectedResponse = new HashMap<String, String>();
		expectedResponse.put(RequestBodyAndResponseData.NameKey, RequestBodyAndResponseData.NameValue); 
		expectedResponse.put(RequestBodyAndResponseData.SalaryKey, RequestBodyAndResponseData.SalaryValue); 
		expectedResponse.put(RequestBodyAndResponseData.AgeKey, RequestBodyAndResponseData.AgeValue);
		return expectedResponse;
	}

	public static List<Map<String, String>> expectedGetResponse (String jsonNode) throws Exception {
		String localDir = System.getProperty("user.dir");
		FileReader file = new FileReader(localDir + RequestBodyAndResponseData.GetResponseFile);
		Object obj = new JSONParser().parse(file);
		JSONObject jo = (JSONObject) obj; 
		List<Map<String, String>> ExpectedData = (JSONArray) jo.get(jsonNode);
		return ExpectedData;
	}
}
