/**
 * @author prgupta
 */
package constants;

import java.util.HashMap;
import java.util.Map;

public interface TestSuiteConstants {

	public static final String GetEndPoint = "/api/v1/employees";
	public static final String PostEndPoint = "/api/v1/create";
	public static final String DeleteEndPoint = "/api/v1/delete/";

	public class RequestBodyAndResponseData {
		
		public static final String IDKey = "id";
		public static final String StatusKey = "status";
		public static final String DataKey = "data";
		public static final String NameKey = "name";
		public static final String SalaryKey = "salary";
		public static final String AgeKey = "age";
		public static final String MessageKey = "message";

		public static final String StatusValue = "success";
		public static final String NameValue = "John";
		public static final String SalaryValue = "500";
		public static final String AgeValue = "23";
		public static final String SuccessMessageValue = "successfully! deleted Records";
		
		public static final String GetResponseFile = "\\src\\main\\java\\testData\\ExpectedGetCallResponse.json";
		public static final String EmployeeName = "employee_name";
		public static final String EmployeeSalary = "employee_salary";
		public static final String EmployeeAge = "employee_age";
		public static final String ProfileImage = "profile_image";
	}
}
