package POM_task.Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import POM_task.Utils.ConfigFileReader;

public class Listeners implements ITestListener {
	protected static ExtentReports report;
	protected static ExtentSparkReporter reports;
	protected static ExtentTest test;
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		//ITestListener.super.onTestStart(result);
		System.out.println(result.getMethod().getMethodName()+" Test Started ");
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "test is started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println(result.getMethod().getMethodName()+" Test Passed ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//TestListener.super.onTestFailure(result);
		System.out.println(result.getMethod().getMethodName()+" Test Failed ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
		System.out.println(result.getMethod().getMethodName()+" Test Skipped ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
		System.out.println(result.getMethod().getMethodName()+ " Test Success with % ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test failed with success percentage");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
		System.out.println(result.getMethod().getMethodName()+" Test failed with timeout ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test failed with timeout ");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
		System.out.println("Test Suite :" + context.getName()+" started.");
		report = new ExtentReports();
		reports = new ExtentSparkReporter(ConfigFileReader.prop.getProperty("ReportPath"));
		report.attachReporter(reports);
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		System.out.println(" Test Suite" + context.getName()+" Finished ");
		
		report.flush();
	}

}
