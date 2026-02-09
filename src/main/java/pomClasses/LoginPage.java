package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='user-name']")
	private WebElement userName;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@id='login-button']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[@class='error-message-container']")
	private WebElement errorMessage;
		
	
	public void logginginSaucedemo(String name,String pass)
	{
		userName.sendKeys(name);
		password.sendKeys(pass);
		loginButton.click();	
	}
	
	public boolean isProductsPageDisplayed() {
	    return driver.getCurrentUrl().contains("inventory.html");
	}

	public boolean isLockedUserErrorDisplayed() {
	    return errorMessage.getText()
	            .contains("Sorry, this user has been locked out");
	}
	
	
}
