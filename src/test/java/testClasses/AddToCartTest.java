package testClasses;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pomClasses.AddToCart;
import pomClasses.HomePage;

public class AddToCartTest extends BaseTest{

	//AddToCart TestCases
	//Verify that clicking “Add to cart” adds product to shopping cart
	//Verify adding multiple products increments cart count correctly
	//Verify adding the same product multiple times increments cart quantity
	//Verify cart retains added products on page reload
	//Verify user can navigate to cart after adding product
	//Verify the “Add to cart” button changes to “Remove” after adding product
	
	
//	@Test
//	public void VerifyProductAddinAddToCartAfterClickAddToCart() {
//		HomePage homePage = new HomePage(driver);
//		String productNameAddCart = homePage.getProductNames("Sauce Labs Bike Light");
//		homePage.clickonProductAddToCartBtn("Sauce Labs Bike Light");
//		AddToCart addToCart = homePage.goToAddToCartPage(driver);
//		List<String> inCartProductList = addToCart.getProductNames();
//		//Assert.assertEquals(productNameAddCart, inCartProductList);
//		for(int i=0;i<=inCartProductList.size();i++)
//		{
//			Assert.assertEquals(productNameAddCart, inCartProductList.get(i));
//		}
//	}
	
	@Test
	public void VerifyProductAddinAddToCartAfterClickAddToCart() {

	    HomePage homePage = new HomePage(driver);
	    String expectedProduct = "Sauce Labs Bike Light";

	    homePage.clickonProductAddToCartBtn(expectedProduct);

	    AddToCart addToCart = homePage.goToAddToCartPage(driver);
	    List<String> inCartProductList = addToCart.getProductNames();

	    Assert.assertTrue(inCartProductList.contains(expectedProduct),"Expected product is not present in cart");
	}
	
}
