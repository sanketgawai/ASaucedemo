package pomClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCart {

	WebDriver driver;
	
	public AddToCart(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@class='cart_item']/div/a")
	private List<WebElement> listofAllProductName;
	
	@FindBy(xpath="//div[text()='Sauce Labs Bike Light']/parent::a")
	private WebElement cartProductName;
	
	
	public List<String> getProductNames()
	{
		List<String> productes = new ArrayList<String>();
		for(WebElement p : listofAllProductName)
		{
			productes.add(p.getText()); 
		}
		return productes;
	}
	
}
