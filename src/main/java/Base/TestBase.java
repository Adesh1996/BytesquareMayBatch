package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pages.LoginPage;
import Utility.PropertiesReader;

public class TestBase {
	

	
	public static  WebDriver driver;	
	
	@BeforeMethod
	public  static void InitilaizeDriver() throws InterruptedException {
		
		String browser = PropertiesReader.readPropertiesFile("browserType");
		
		if (browser.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else {
			driver= new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		
		driver.get(PropertiesReader.readPropertiesFile("application.URL"));
		
		Thread.sleep(10000);
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
