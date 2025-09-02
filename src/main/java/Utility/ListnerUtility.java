package Utility;



import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Base.TestBase;

public class ListnerUtility extends TestBase implements ITestListener{
	
	String sparkReportPath = System.getProperty("user.dir") + "\\Reports\\SparkReport.html";
	
	ExtentSparkReporter spark = new ExtentSparkReporter(sparkReportPath);
	ExtentReports extent = new ExtentReports();
	
	
	@Override
	public void onStart(ITestContext context) {
		extent.attachReporter(spark);
		spark.config().setReportName("ORANGE Result");
		spark.config().setDocumentTitle("Test Automation Result");
		spark.config().setTheme(Theme.STANDARD);
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Execuation is started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		test.pass(result.getMethod().getMethodName() + "TestCase PASS");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String filepath = System.getProperty("user.dir") + "\\Reports\\embedded.png";
		
		File DestFile = new File(filepath);
		
		try {
			FileHandler.copy(srcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		test.fail(result.getMethod().getMethodName() + "Test Case failed").addScreenCaptureFromPath(filepath);
		test.log(Status.FAIL, result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		test.skip(result.getMethod().getMethodName() + "TestCase Skip");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
