package abstractcomponant;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponant {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;
	
	public AbstractComponant(WebDriver driver)
	{
		this.driver = driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		js = (JavascriptExecutor)driver;
		act = new Actions(driver);
	}
	
	
	public void waitForElementToAppearByLocator(By locator)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementToAppearByWebElement(WebElement ele)
	{
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToAppearUsingListOfWebElement(List<WebElement> products)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(products));
	}
	
	public void waitForElementToWebElementisClickable(WebElement ele)
	{
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void scrollDown()
	{
	    js.executeScript("window.scrollBy(0,500)");
	}
	
	public void scrollToTopOfThePage()
	{
		js.executeScript("window.scrollTo(0,0);");
	}
	
	public void scrollIntoView(WebElement ele) 
    {
	    js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }
	
	public void moveToElementAndClick(WebElement ele) 
	{
		act.moveToElement(ele).click().build().perform();
	}
	
	public void clickOnAndOpenInAnotherTabUsingKey(WebElement ele)
	{
		ele.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
	}
	
	public void clickOnAndOpenInAnotherTabUsingActionClass(WebElement ele)
	{
		act.keyDown(Keys.CONTROL).click().keyUp(Keys.CONTROL).build().perform();
	}
	
	public void moveToWebElementAndClickOnItUsingActionClass(WebElement ele)
	{
		act.moveToElement(ele).click().build().perform();
	}
	
	public void clickOnWebElementUsingJavascriptExecutor(WebElement ele)
	{
		js.executeScript("arguments[0].click()", ele);
	}
	
	public void switchWindowByIndex(int no)
	{
		ArrayList<String> addr = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(addr.get(no));
	}
	
	public void switchToNewWindow(Set<String> oldWindows)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(d->d.getWindowHandles().size()>oldWindows.size());
		
		Set<String> newWindows = driver.getWindowHandles();
		newWindows.removeAll(oldWindows);
		
		if(newWindows.size()==1)
		{
			driver.switchTo().window(newWindows.iterator().next());
		}
		else
		{
			throw new RuntimeException("Unexpected number of new windows: " + newWindows.size());
		}
	}
	//***** this is in pomclass this use it like this 
//	public InstagramLoginPage clickOnInstagram()
//	{
//		Set<String> oldWindows = driver.getWindowHandles();  // capture BEFORE click
//		clickOnAndOpenInAnotherTabUsingKey(instagram);
//		switchToNewWindow(oldWindows);
//		return new InstagramLoginPage(driver);
//	}
	
}
