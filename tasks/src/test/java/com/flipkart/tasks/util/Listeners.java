package com.flipkart.tasks.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

public class Listeners extends BaseTest implements ITestListener {


	public void onTestStart(ITestResult result) {
		
		//ITestListener.super.onTestStart(result);
		System.out.println(result.getMethod().getMethodName()+" Test Started ");
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + " test is started");
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println(result.getMethod().getMethodName()+" Test Passed ");
		test.log(Status.INFO, result.getMethod().getMethodName() + " test is passed");
	}

	public void onTestFailure(ITestResult result) {
		
		//TestListener.super.onTestFailure(result);
		System.out.println(result.getMethod().getMethodName()+" Test Failed ");
		test.log(Status.INFO, result.getMethod().getMethodName() + " test failed");
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath( "\\reports\\Login_Failed.png" ).build());
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
		System.out.println(result.getMethod().getMethodName()+" Test Skipped ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test Skipped");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
		System.out.println(result.getMethod().getMethodName()+ " Test Success with % ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test failed with success percentage");
	}

	
	public void onTestFailedWithTimeout(ITestResult result) {
	
		//ITestListener.super.onTestFailedWithTimeout(result);
		System.out.println(result.getMethod().getMethodName()+" Test failed with timeout ");
		test.log(Status.INFO, result.getMethod().getMethodName() + "test failed with timeout ");
	}

	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
		System.out.println("Test Suite :" + context.getName()+" started.");
		report = new ExtentReports();
		reports = new ExtentSparkReporter(getData().getProperty("ReportPath"));
		report.attachReporter(reports);
		
	}


	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		System.out.println(" Test Suite" + context.getName()+" Finished ");
		
		report.flush();
	}
	
	
	
	
}
