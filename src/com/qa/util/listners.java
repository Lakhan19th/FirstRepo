package com.qa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

public class listners implements ITestListener, ISuiteListener
{

	
	
	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		Reporter.log("About to end executing automation suite "+arg0.getName(),true);
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		Reporter.log("About to begin executing automation suite "+arg0.getName(),true);
	}

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		Reporter.log("Completed execution test "+arg0.getName(),true);
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		Reporter.log("About to begin executing test "+arg0.getName(),true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		Reporter.log("Test failed "+arg0.getName(),true);
	
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		Reporter.log("Test skipped "+arg0.getName(),true);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Reporter.log("Test---"+arg0.getName()+"--is started at---"+dateFormat.format(date),true);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		Reporter.log("Test success "+arg0.getName(),true);
	}

	
	
	private String returnMethodName(ITestNGMethod method)
	{
		return method.getRealClass().getSimpleName()+"."+method.getMethodName();
	}
	
	
	
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1)
	{
	String textMsg = "About to beging executing following method"+returnMethodName(arg0.getTestMethod());	
	Reporter.log(textMsg,true);
	}
	
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1)
	{
	String textMsg = "Completed executing following method"+returnMethodName(arg0.getTestMethod());	
	Reporter.log(textMsg,true);
	}

	
}
