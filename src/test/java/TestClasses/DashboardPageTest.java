package TestClasses;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.DashboardPage;
import Pages.PerformacePage;

public class DashboardPageTest extends TestBase{
	
	DashboardPage dashboardPage;

	@Test(description = "To verify All the panesl Should be visible on Dashbaord Page",groups="Regression")
	public void panelCheck() {
		dashboardPage = new DashboardPage(driver);
		dashboardPage.clickOnLinkDashboard();
		
		Assert.assertEquals(dashboardPage.panelTimeAtWork.getText(), "Time at Work", "Expected is not matchec with Actual");
		Assert.assertEquals(dashboardPage.panelMyActions.getText(), "My Actions", "Expected is not matchec with Actual");
		
	}
	

	@Test(dataProvider="getData",dataProviderClass = Utility.DataProviderUtils.class, groups="Regression")
	public void CheckMyActionsPanel(Map<String,String> data) throws InterruptedException {
		dashboardPage = new DashboardPage(driver);
		dashboardPage.clickOnPendingSelfReview();
		PerformacePage performacePage = new PerformacePage(driver);
		Thread.sleep(10000);
		Assert.assertTrue(performacePage.headerPerformanceManageReview.isDisplayed());
		
		System.out.println(data.get("EmployeeName"));
	}
	
}
