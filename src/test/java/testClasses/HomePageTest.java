package testClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pomClasses.AddToCart;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class HomePageTest {

	WebDriver driver;
	LoginPage loginPage;
    HomePage homePage;
	
//	@BeforeMethod
//    public void loginAndInitPages() {
//        loginPage = new LoginPage(driver);
//        homePage = new HomePage(driver);
//    }
	
	//public void login(WebDriver driver)
//	public void login()
//	{
//		loginPage.logginginSaucedemo("standard_user","secret_sauce");
//	}
	
	@BeforeClass
	public void login() throws IOException
	{	BaseTest baseTest = new BaseTest();
		driver = baseTest.initializeDriver();
	 	driver.get(baseTest.getProperties("url_saucedemo"));
		loginPage = new LoginPage(driver);
	    homePage  = new HomePage(driver);
		loginPage.logginginSaucedemo("standard_user","secret_sauce");
	}
	
	
	@Test()
	public void verifyProductsPageLoadsAfterLogin()
	{
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");		
	}
	
	@Test()
	public void verifyProductsPageTitle()
	{
		System.out.println("homePageTitle : "+homePage.getTitle());
		Assert.assertEquals(homePage.getTitle(),"Sauce Labs Backpack");
	}
	
	@Test()
	public void verifySwagLabsLogoisDisplayed()
	{
		Assert.assertEquals(homePage.verifyLogo(), true);
	}
	
	@Test
	public void VerifyAllProductsareDisplayed()
	{
		System.out.println("countofAllProduct : "+homePage.countofAllProduct());
		Assert.assertEquals(homePage.countofAllProduct(),6);
	}
	
	//scenario-Product Details, //TestCase-Verify each product has name, price and image
	@Test
	public void verifyEachProductHasNamePriceAndImage() {

	    boolean result = homePage.verifyEachProductHasNamePriceAndImage();
	    Assert.assertTrue(result, "One or more products are missing name, price, or image");
	}
	
	
	public void verifyProductEachNamesareUnique()
	{
		boolean result = homePage.verifyProductNamesAreUnique();
		Assert.assertTrue(result, "One or more product names are duplicate!");
	}
	
	@Test
	public void verifyNameZtoAsorting()
	{
		
		//homePage.sortWithZtoA();
		homePage.sortWithZtoA();

	    List<String> actualList = homePage.getAllProductNames();
	    List<String> expectedList = new ArrayList<>(actualList);

	    // Sort expected list in reverse (Z to A)
	    expectedList.sort(Collections.reverseOrder());
	    
	    System.out.println("A-Z : "+actualList);
	    System.out.println("Z-A : "+expectedList);
	    Assert.assertEquals(actualList, expectedList,"Products are NOT sorted in Z to A order");
	}
	
}
