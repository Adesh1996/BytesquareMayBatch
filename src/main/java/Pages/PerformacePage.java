package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;

public class PerformacePage extends TestBase{

	

	public WebDriver driver;
	
	public PerformacePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="(//div[@class='oxd-topbar-header-title'])[1]")
	public WebElement headerPerformanceManageReview;
}
