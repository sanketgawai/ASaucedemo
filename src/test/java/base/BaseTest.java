package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.LoginPage;

public class BaseTest {

	protected WebDriver driver;
	
	 public WebDriver initializeDriver() throws IOException {

        String browserName = getProperties("browserName").trim();

        if (browserName.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-save-password-bubble");

            driver = new ChromeDriver(options);

        } 
//        else if (browserName.equalsIgnoreCase("edge")) {
//
//            WebDriverManager.edgedriver().setup();
//
//            EdgeOptions options = new EdgeOptions();
//            options.addArguments("--disable-notifications");
//            options.addArguments("--disable-infobars");
//
//            driver = new EdgeDriver(options);
//
//        }
        else if (browserName.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");

            driver = new EdgeDriver(options);
        }
        else if (browserName.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);

            driver = new FirefoxDriver(options);
        }
        

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();

        return driver;
	 }
	
	 @BeforeMethod
	 public void initializeDriverwithBeforeMethod() throws IOException
	 {
		 initializeDriver();
		 driver.get(getProperties("url_saucedemo"));
	 }
	 
	public String getProperties(String key) throws IOException
	{
		Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\resource\\global.properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot t = (TakesScreenshot)driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+"+.png");
		//FileHandler.copy(src, dest);
		FileUtils.copyFile(src, dest);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+"+.png";
	}
	
//	public void login(WebDriver driver)
//	{
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.logginginSaucedemo("standard_user","secret_sauce");
//	}
	
}
