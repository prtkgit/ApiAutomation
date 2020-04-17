/**@author prgupta
 * 
 */
package contextManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import constants.ProjectConstants;
import extentManager.ExtentHtmlReporterHelper;

public class AutomationContextManager {
	private static final Logger logger = LoggerFactory.getLogger(AutomationContextManager.class);
	private static String URL;
	private static String Protocol;
	private static ExtentTest ExtentTest;
	private static ExtentReports ExtentReport;

	public static ExtentReports getExtentReport() {
		return ExtentReport;
	}
	public static void setExtentReport(ExtentReports extentReport) {
		extentReport.attachReporter(ExtentHtmlReporterHelper.htmlReportGenerator());
		ExtentReport = extentReport;
	}

	public static ExtentTest getExtentTest() {
		return ExtentTest;
	}
	public static void setExtentTest(ExtentTest extentTest) {
		ExtentTest = extentTest;
	}


	public static String getProtocol() {
		return Protocol;
	}
	public static void setProtocol() {
		Protocol =  ProjectConstants.Protocol;
		logger.info("Protocol Set for the Execution is "+ Protocol);
	}

	public static String getURL() {	
		return URL;
	}
	public static void setURL() {
		URL = ProjectConstants.ApplicationURL;
		logger.info("URL Set for the Execution is "+ URL);
	}
}

