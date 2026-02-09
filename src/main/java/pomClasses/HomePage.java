package pomClasses;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponant.AbstractComponant;

public class HomePage extends AbstractComponant{

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//🔹 HEADER
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
	WebElement hamburgerMenuBtn;

	@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
	WebElement cartIcon;

	@FindBy(xpath = "//span[@data-test='title']")
	WebElement pageTitle;
	
	//🔹 SIDEBAR MENU
	
	@FindBy(xpath = "//button[@id='react-burger-cross-btn']")
	WebElement closeMenuBtn;

	@FindBy(xpath = "//a[@data-test='inventory-sidebar-link']")
	WebElement allItemsLink;

	@FindBy(xpath = "//a[@data-test='about-sidebar-link']")
	WebElement aboutLink;

	@FindBy(xpath = "//a[@data-test='logout-sidebar-link']")
	WebElement logoutLink;

	@FindBy(xpath = "//div[@class='app_logo']")
	WebElement swagLabsLogo;
	
	@FindBy(xpath = "//a[@data-test='reset-sidebar-link']")
	WebElement resetAppStateLink;
	
	//PRODUCT LIST (COMMON ELEMENTS)
	
	@FindBy(xpath = "//div[@data-test='inventory-item']")
	List<WebElement> inventoryItems;

	@FindBy(xpath = "//div[@data-test='inventory-item-name']")
	List<WebElement> productNames;

	@FindBy(xpath = "//div[@data-test='inventory-item-desc']")
	List<WebElement> productDescriptions;

	@FindBy(xpath = "//div[@data-test='inventory-item-price']")
	List<WebElement> productPrices;
	
	//🔹 ADD TO CART BUTTONS
	//All AddToCartButton
	//div[text()='Sauce Labs Bike Light']/parent::a/parent::div/following-sibling::div/div/following-sibling::button
	String name = "Sauce Labs Bike Ligh";
	@FindBy(xpath = "//div[text()='Sauce Labs Bike Light']/parent::a/parent::div/following-sibling::div/div/following-sibling::button")
	WebElement allAddToCartBtn;
	
	public WebElement getAllProductName(String productName) {
	    return driver.findElement(By.xpath("//div[text()='productName']"));
	}
	public WebElement getAllAddToCartButton(String productName) {
	    return driver.findElement(By.xpath("//div[text()='"+productName+"']/parent::a/parent::div/following-sibling::div/div/following-sibling::button"));
	}
	
	@FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-backpack']")
	WebElement addBackpackBtn;

	@FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-bike-light']")
	WebElement addBikeLightBtn;

	@FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']")
	WebElement addBoltTshirtBtn;

	@FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")
	WebElement addFleeceJacketBtn;

	@FindBy(xpath = "//button[@data-test='add-to-cart-sauce-labs-onesie']")
	WebElement addOnesieBtn;

	@FindBy(xpath = "//button[@data-test='add-to-cart-test.allthethings()-t-shirt-(red)']")
	WebElement addRedTshirtBtn;

	
	//REMOVE FROM CART BUTTONS
	
	@FindBy(xpath = "//button[@data-test='remove-sauce-labs-backpack']")
	WebElement removeBackpackBtn;

	@FindBy(xpath = "//button[@data-test='remove-sauce-labs-bike-light']")
	WebElement removeBikeLightBtn;
	
	//	/🔹 PRODUCT NAVIGATION
	@FindBy(xpath = "//a[@data-test='item-4-img-link']")
	WebElement backpackImageLink;

	@FindBy(xpath = "//a[@data-test='item-4-title-link']")
	WebElement backpackTitleLink;
	
	//FOOTER LINKS
	@FindBy(xpath = "//a[@data-test='social-twitter']")
	WebElement twitterLink;

	@FindBy(xpath = "//a[@data-test='social-facebook']")
	WebElement facebookLink;

	@FindBy(xpath = "//a[@data-test='social-linkedin']")
	WebElement linkedinLink;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement AddToCartField;
	
	public String getTitle()
	{
		return backpackTitleLink.getText();
	}
	
	public boolean verifyLogo()
	{
		return swagLabsLogo.isDisplayed();
	}
	
	public int countofAllProduct()
	{
		return inventoryItems.size();
	}
	
	public String getProductNames(String productName)
	{
		 return getAllProductName(productName).getText();
	}
	
	public void clickonProductAddToCartBtn(String productName) {
		//addBikeLightBtn.click();
		getAllAddToCartButton(productName);
	}
	
	public AddToCart goToAddToCartPage(WebDriver driver)
	{
		waitForElementToWebElementisClickable(AddToCartField);	
		Set<String> oldWindows = driver.getWindowHandles();
		clickOnAndOpenInAnotherTabUsingKey(AddToCartField);
		switchToNewWindow(oldWindows);
		return new AddToCart(driver);
	}
}
