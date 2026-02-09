package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class HomePageTest extends BaseTest{

	//WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeMethod
    public void loginAndInitPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }
	
	public void login(WebDriver driver)
	{
		loginPage.logginginSaucedemo("standard_user","secret_sauce");
	}
	
	@Test(enabled=false)
	public void verifyProductsPageLoadsAfterLogin()
	{

		login(driver);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");		
	}
	
	@Test(enabled=false)
	public void verifyProductsPageTitle()
	{
		login(driver);
		//homePage = new HomePage(driver);
		System.out.println("homePageTitle : "+homePage.getTitle());
		Assert.assertEquals(homePage.getTitle(),"Sauce Labs Backpack");
	}
	
	@Test()
	public void verifySwagLabsLogoisDisplayed()
	{
		login(driver);
		Assert.assertEquals(homePage.verifyLogo(), true);
	}
	
	@Test
	public void VerifyAllProductsareDisplayed()
	{
		login(driver);
		System.out.println("countofAllProduct : "+homePage.countofAllProduct());
		Assert.assertEquals(homePage.countofAllProduct(),6);
	}
	
}
