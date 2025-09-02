package Utility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Base.TestBase;

public class CommonActions extends TestBase {

	public void clickonElement(WebElement element) {
		   visibilityOfelement(element);
			highlightElement(element);
			element.click();
		
	}

	private void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px dashed red'", element);
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public void enters_data(String value, WebElement element) {
		visibilityOfelement(element);
		highlightElement(element);
		element.sendKeys(value);
	}

	public static boolean visibilityOfelement(WebElement element) {

		return element.isDisplayed();
	}

	
	public void clickUsingJs(WebElement element) {
		wait(2000);
		visibilityOfelement(element);

		if (element.isDisplayed() && element.isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			highlightElement(element);
			js.executeScript("argument[0].click();", element);
		}
		wait(2000);
	}
	
	
	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Assert.fail(e.getLocalizedMessage());
			Thread.currentThread().interrupt();
		}
	}
	
	public  void waitFor_ElementVisibility(By value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			wait.until(ExpectedConditions.visibilityOfElementLocated(value));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void moveToAndClick(WebElement element) {
		Actions act = new Actions(driver);
		wait(2000);
		act.moveToElement(element).click().build().perform();
	}

	public String getTitle(WebDriver driver) {
		String title = driver.getTitle();
		System.out.println("Title of Page : " + title);
		return title;
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public void acceptAlert() {
		 Alert alert = driver.switchTo().alert();
		alert.accept();
	}
}
