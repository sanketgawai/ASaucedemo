package testClasses;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pomClasses.AddToCart;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class AddToCartTest {

	WebDriver driver;
	LoginPage loginPage;
    HomePage homePage;
    
	//AddToCart TestCases
	//Verify that clicking “Add to cart” adds product to shopping cart
	//Verify adding multiple products increments cart count correctly
	//Verify adding the same product multiple times increments cart quantity
	//Verify cart retains added products on page reload
	//Verify user can navigate to cart after adding product
	//Verify the “Add to cart” button changes to “Remove” after adding product
	
	
	@BeforeClass
	public void login() throws IOException
	{	BaseTest baseTest = new BaseTest();
		driver = baseTest.initializeDriver();
	 	driver.get(baseTest.getProperties("url_saucedemo"));
		loginPage = new LoginPage(driver);
	    homePage  = new HomePage(driver);
		loginPage.logginginSaucedemo("standard_user","secret_sauce");
	}
	
//	@Test
//	public void VerifyProductAddinAddToCartAfterClickAddToCart() {
//
//	    HomePage homePage = new HomePage(driver);
//	    String expectedProduct = "Sauce Labs Bike Light";
//					
//	    homePage.clickonProductAddToCartBtn(expectedProduct);
//
//	    AddToCart addToCart = homePage.goToAddToCartPage(driver);
//	    List<String> inCartProductList = addToCart.getProductNames();
//
//	    Assert.assertTrue(inCartProductList.contains(expectedProduct),"Expected product is not present in cart");
//	}
	
	//test case - Verify all products are displayed
	@Test(enabled=false)
	public void VerifyProductAddinAddToCartAfterClickAddToCart() {

	    HomePage homePage = new HomePage(driver);
	    String expectedProduct = "Sauce Labs Bike Light";
	    String [] expectedProducts = {"Sauce Labs Bike Light","Sauce Labs Fleece Jacket","Test.allTheThings() T-Shirt (Red)","Sauce Labs Backpack"};
	    for(int i=0;i<=expectedProducts.length-1;i++)
	    {
	    	homePage.clickonProductAddToCartBtn(expectedProducts[i]);
	    }
	    

	    AddToCart addToCart = homePage.goToAddToCartPage(driver);
	    List<String> inCartProductList = addToCart.getProductNames();

	    Assert.assertTrue(inCartProductList.contains(expectedProduct),"Expected product is not present in cart");
	  
	    //=========================================================//
	    for (int i = 0; i < expectedProducts.length; i++) {
	        boolean isPresent = inCartProductList.contains(expectedProducts[i]);
	        System.out.println(
	            "Product added: " + expectedProducts[i] +
	            " | Present in cart: " + isPresent
	        );
	    }
	    
	    
	}
	
	@Test(enabled=false)
	public void verifySingleProductAddedToCart() {

	    String expectedProduct = "Sauce Labs Bike Light";

	    homePage.clickonProductAddToCartBtn(expectedProduct);
	    AddToCart addToCart = homePage.goToAddToCartPage(driver);

	    List<String> cartProducts = addToCart.getProductNames();

	    Assert.assertTrue(cartProducts.contains(expectedProduct),
	            "Product was not added to cart");
	}
	
	//Verify cart count updates
	@Test(enabled=false)
	public void verifyMultipleProductsAddedToCart() {

	    String[] products = {
	            "Sauce Labs Bike Light",
	            "Sauce Labs Backpack",
	            "Sauce Labs Fleece Jacket"
	    };

	    for (String product : products) {
	        homePage.clickonProductAddToCartBtn(product);
	    }

	    AddToCart addToCart = homePage.goToAddToCartPage(driver);
	    List<String> cartProducts = addToCart.getProductNames();

	    Assert.assertEquals(cartProducts.size(), products.length,
	            "Cart count does not match number of added products");
	}
	
	//Verify user can remove product
	@Test(enabled=false)
	public void verifyRemoveProductfromCart() {

	    String expectedProduct = "Sauce Labs Bike Light";

	    homePage.clickonProductAddToCartBtn(expectedProduct);
	    AddToCart addToCart = homePage.goToAddToCartPage(driver);

	    List<String> cartProducts = addToCart.getProductNames();

	    Assert.assertTrue(cartProducts.contains(expectedProduct),
	            "Product was not added to cart");
	    
	    addToCart.clickOnRemoveButtonInCratPage(expectedProduct);
	    
	 // 🔥 Re-fetch product list after removal
	    List<String> updatedCartProducts = addToCart.getProductNames();

	    // Validate product removed
	    Assert.assertFalse(updatedCartProducts.contains(expectedProduct),
	            "Product was not removed from cart");
	}
	
	//Verify navigation to Cart page
	public void verifyNavigationtoCartpage() {
		
		  AddToCart addToCart = homePage.goToAddToCartPage(driver);
		  Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
		  
	}
	
	// Test case - Verify selected products appear in cart
	@Test(enabled=false)
	public void verifySelectedProductsAppearInCart() {

	    // Select products to add
	    String[] selectedProducts = {
	            "Sauce Labs Bike Light",
	            "Sauce Labs Backpack",
	            "Sauce Labs Fleece Jacket"
	    };

	    // Add selected products to cart
	    for (String product : selectedProducts) {
	        homePage.clickonProductAddToCartBtn(product);
	    }

	    // Navigate to cart page
	    AddToCart addToCart = homePage.goToAddToCartPage(driver);

	    // Get list of products in cart
	    List<String> cartProducts = addToCart.getProductNames();

	    // Verify each selected product is present in cart
	    for (String product : selectedProducts) {
	        Assert.assertTrue(cartProducts.contains(product),
	                "Product not found in cart: " + product);
	        System.out.println("Verified product in cart: " + product);
	    }
	}
	
	
	
}
