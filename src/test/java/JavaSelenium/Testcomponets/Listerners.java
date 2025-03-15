package JavaSelenium.Testcomponets;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import JavaSelenium.resources.ExtendtreportNg;

public class Listerners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent= ExtendtreportNg.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // assign some uniques thread (eg. Errorvalidation ->> test>
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable()); // Log failure reason
	    
	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	        String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
	        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // Attach screenshot to report
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}
	
	
}