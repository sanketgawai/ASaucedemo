package resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public ExtentReports getReportsObject()
	{
		String path = System.getProperty("user.dir"+"//reports//reportindex.html");
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Automation Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sanket");
		extent.setSystemInfo("Envirnonment", "QA");
		extent.setSystemInfo("Browser", "Chrome");
		
		return extent;
	}
}
