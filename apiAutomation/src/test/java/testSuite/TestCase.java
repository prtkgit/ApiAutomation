/**@author prgupta
 * 
 */

package testSuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import apiHelper.DeleteCallManager;
import apiHelper.TestCaseHelperFile;
import common.HTTPMethods;
import constants.ProjectConstants.HttpCode;
import constants.TestSuiteConstants;
import io.restassured.response.Response;
import testData.ExpectedTestData;

public class TestCase extends HTTPMethods{
	
//	@Test(groups= "automationSuite")
//	public void getCallTestCase() throws Exception {
//		Response actualResponse = getCall("Content-Type", "application/json",TestSuiteConstants.GetEndPoint);
//		Assert.assertEquals(actualResponse.getStatusCode(), HttpCode.OK); 
//		TestCaseHelperFile.validateGetCallResponse(actualResponse, ExpectedTestData.expectedGetResponse("data"));		
//	}	
//	
	@Test(groups= "automationSuite")
	public void postCallTestCase() {
		Response actualResponse = postCall("Content-Type", "application/json",TestSuiteConstants.PostEndPoint,
				TestCaseHelperFile.generateRequestBody());
		Assert.assertEquals(actualResponse.getStatusCode(), HttpCode.OK); 
		TestCaseHelperFile.validatePostCallResponse(actualResponse, ExpectedTestData.expectedPostResponse());
	}	
	
	@Test(groups= "automationSuite", dependsOnMethods = "postCallTestCase")
	public void deleteCallTestCase() {
		Response actualResponse = deleteCall("Content-Type", "application/json",TestSuiteConstants.DeleteEndPoint,
				DeleteCallManager.getIdToBeDeleted());
		Assert.assertEquals(actualResponse.getStatusCode(), HttpCode.OK); 
		TestCaseHelperFile.validateDeleteCallResponse(actualResponse);		
	}	
}