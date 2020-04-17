/**
 * @author prgupta
 */
package listeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import contextManager.AutomationContextManager;

public class DriverListeners implements  IInvokedMethodListener, IExecutionListener{
	private static final Logger logger = LoggerFactory.getLogger(DriverListeners.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		if(method.isTestMethod())
		{
			AutomationContextManager.setExtentTest(AutomationContextManager.getExtentReport().createTest(testResult.getName()));
			AutomationContextManager.setProtocol();
			AutomationContextManager.setURL();
			AutomationContextManager.getExtentTest().info("Execution of Test case Started");
			AutomationContextManager.getExtentTest().info("Location of test case in suite -> "+testResult.getInstanceName()+"."+testResult.getName());
			AutomationContextManager.getExtentTest().info("Environment Details ->"+AutomationContextManager.getProtocol()+AutomationContextManager.getURL());
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		AutomationContextManager.getExtentTest().info("Execution of Test case Finished");
		if(method.isTestMethod())
		{
			if(testResult.getStatus() == ITestResult.FAILURE) {
				AutomationContextManager.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(testResult.getName()+" FAILED ", ExtentColor.RED));
				AutomationContextManager.getExtentTest().fail(testResult.getThrowable());
			}
			else if(testResult.getStatus() == ITestResult.SUCCESS) {
				AutomationContextManager.getExtentTest().log(Status.PASS, MarkupHelper.createLabel(testResult.getName()+" PASSED ", ExtentColor.GREEN));
			}
			else {
				AutomationContextManager.getExtentTest().log(Status.SKIP, MarkupHelper.createLabel(testResult.getName()+" SKIPPED ", ExtentColor.ORANGE));
				AutomationContextManager.getExtentTest().skip(testResult.getThrowable());
			}
		}
		AutomationContextManager.getExtentReport().flush();
	}

	@Override
	public void onExecutionStart() {
		ExtentReports extent = new ExtentReports();
		AutomationContextManager.setExtentReport(extent);
	}

	@Override
	public void onExecutionFinish() {
		AutomationContextManager.getExtentReport().flush();
		System.out.println(System.getProperty("user.dir") +"/test-output/testReport.html");
	}
}