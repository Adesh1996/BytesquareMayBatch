package TestClasses;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;
import Pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	
@Test(groups="Regression", dataProvider="getData",dataProviderClass = Utility.DataProviderUtils.class)
	public void testLoginPage(Map<String,String> data) {
	
	loginPage = new LoginPage(driver);
	Assert.assertEquals(loginPage.userLink.getText(), data.get("UserName") , "User is not matched");
	}
}
