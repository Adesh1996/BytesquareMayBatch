package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;
import Utility.CommonActions;
import Utility.PropertiesReader;

public class LoginPage extends TestBase{
	
	
	public WebDriver driver;
	CommonActions commonActions;
	
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@name='username']")  
	public WebElement textBoxUsername;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement textBoxPassword;
	
	@FindBy(xpath="//button[normalize-space()='Login']")
	public WebElement buttonLogin;
	
	@FindBy(xpath="//span[@class='oxd-userdropdown-tab']//p")
	public WebElement userLink;
	
	
	
	public void loginToApplication()  {
		
		commonActions = new CommonActions();
		commonActions.enters_data(PropertiesReader.readPropertiesFile("App.Username"), textBoxUsername);
		commonActions.enters_data(PropertiesReader.readPropertiesFile("User.Password"), textBoxPassword);
		
		
		commonActions.wait(5000);
		
		commonActions.clickonElement(buttonLogin);
	
		
	}


}
