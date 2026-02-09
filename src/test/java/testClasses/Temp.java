package testClasses;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Temp {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		 // 🔹 Disable Chrome password & security popups
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-save-password-bubble");
        
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		
		String name = "username";
		
		WebElement userName = driver.findElement(By.xpath("//input[@data-test='"+name+"']"));
		WebElement passWord = driver.findElement(By.xpath("//input[@data-test='password']"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@data-test='login-button']"));
		WebElement errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container']"));
		
		userName.sendKeys("standard_user");
		passWord.sendKeys("secret_sauce");
		loginButton.click();
		
		
		
		
		
	}
	
}
