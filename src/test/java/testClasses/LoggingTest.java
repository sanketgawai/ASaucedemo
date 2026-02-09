package testClasses;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pomClasses.LoginPage;
import rhl.data.DataDrivenFromExcel;

public class LoggingTest extends BaseTest {

	
	@Test(dataProvider="getLoginCredentials")
	//public void loggin(String username, String password, String expectedResult) throws IOException
	public void loggin(HashMap<String,String> data) throws IOException
	{
		
		LoginPage loginPage = new LoginPage(driver);
		//loginPage.logginginSaucedemo("standard_user", "locked_out_user");
		loginPage.logginginSaucedemo(data.get("username"), data.get("password"));

	    if (data.get("expected").equalsIgnoreCase("SUCCESS")) {
	        Assert.assertTrue(loginPage.isProductsPageDisplayed(),
	                "Products page should be displayed");
	    } 
	    else if (data.get("expected").equalsIgnoreCase("LOCKED")) {
	        Assert.assertTrue(loginPage.isLockedUserErrorDisplayed(),
	                "Locked user error should be displayed");
	    }
	}
	
//	@DataProvider
//	public Object[][] getLoginCredentials() throws IOException
//	{
//		return new Object[][] {
//	        {"standard_user", "secret_sauce", "SUCCESS"},
//	        {"locked_out_user", "secret_sauce", "LOCKED"},
//	        {"problem_user", "secret_sauce", "SUCCESS"},
//	        {"performance_glitch_user", "secret_sauce", "SUCCESS"},
//	        {"error_user", "secret_sauce", "SUCCESS"},
//	        {"visual_user", "secret_sauce", "SUCCESS"}
//	    };
//	    
//	}
	
	@DataProvider
	public Object[][] getLoginCredentials() throws IOException {
	    DataDrivenFromExcel data = new DataDrivenFromExcel();
	    return data.getData();
	}
	
}
