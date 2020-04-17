/**
 * @author prgupta
 */
package common;

import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;


public class JsonParser {

	public String jsonBodyCreation(HashMap<String, String> requestBody) {
		JSONObject json = new JSONObject(); 
		Set<String> keys = requestBody.keySet();
		for(String key: keys) {
			json.put(key, requestBody.get(key));
		} 
		return json.toJSONString();
	}	
}