package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.ExtentReportNG;



public class Listeners extends BaseTest implements ITestListener  {

	WebDriver driver;
	ExtentTest test;
	ExtentReportNG extentReportng = new ExtentReportNG(); 
	ExtentReports extent = extentReportng.getReportsObject(); 
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    // Create test entry in report
    @Override
    public void onTestStart(ITestResult result) {
    	System.out.println("Test Passed: " + result.getName());
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    // Log pass status
    @Override
    public void onTestSuccess(ITestResult result) {
    	System.out.println("Test Passed: " + result.getName());
    	 extentTest.get().log(Status.PASS, "TestPass");
    }

    // Log fail status
    @Override
    public void onTestFailure(ITestResult result) {
    	System.out.println("Starting Test: " + result.getName());
    	extentTest.get().fail(result.getThrowable());
    	try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 String filePath=null;
    	try {
			getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    // Log skipped tests
    @Override
    public void onTestSkipped(ITestResult result) {
    	 System.out.println("Test Skipped: " + result.getName());
    }

    // Flush report after all tests
    @Override
    public void onFinish(ITestContext context) {
    	System.out.println("All Tests Completed: " + context.getName());
        extent.flush();
    }



	
}
