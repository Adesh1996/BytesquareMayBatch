package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.TestBase;
import Utility.CommonActions;

public class DashboardPage extends TestBase{
	

	public WebDriver driver;
	CommonActions commonActions;
	
	public DashboardPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'][normalize-space()='Dashboard']")
	public WebElement linkDashboard;
	
	@FindBy(xpath="//p[normalize-space()='Time at Work']")
	public WebElement panelTimeAtWork;
	
	@FindBy(xpath="//p[normalize-space()='My Actions']")
	public WebElement panelMyActions;
	
	@FindBy(xpath = "//p[normalize-space()='(1) Pending Self Review']")
	public WebElement linkPendingSelfReview;
	
	
	
	public void clickOnLinkDashboard() {
		commonActions = new CommonActions();
		commonActions.clickonElement(linkDashboard);
		
	}
	
	public void clickOnPendingSelfReview() {
		commonActions = new CommonActions();
		commonActions.clickonElement(linkPendingSelfReview);
	
	}
	

}
