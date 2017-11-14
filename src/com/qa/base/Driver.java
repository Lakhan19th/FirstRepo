package com.qa.base;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.qa.login.Login;
import com.qa.util.ScreenShots;
import com.qa.util.TestCaseReading;
import com.qa.util.MailReport;

//import junit.framework.Assert;
import jxl.read.biff.BiffException;

@Listeners(com.qa.util.listners.class)

public class Driver {
	
	HashMap hm = new HashMap();
	
	
	
	WebDriver driver;
	Login objLogin;
	ScreenShots objScreenShots;
	TestCaseReading objTestCaseReading;
	MailReport objUtility;
	
	
	@BeforeSuite
	public void excel() throws BiffException, IOException{
		System.setOut(new PrintStream(new FileOutputStream("ConsoleReport\\ConsoleReport.doc")));
		objTestCaseReading = new TestCaseReading();
		objTestCaseReading.readTestCase();
		hm = objTestCaseReading.hm;
	}
	
	@BeforeClass
	public void initObject(){
		driver.get("https://www.facebok.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@BeforeTest
	public void launchUrl(){
		driver = new FirefoxDriver();
		objLogin = new Login(driver);
		objScreenShots = new ScreenShots();
	}
	
	
	/*@BeforeMethod
	public void cleanFile(){
		File file = new File("");
		file.delete();
	}*/
		
	
	@Test (priority=1)
	public void testLogin(){
		if(hm.containsKey("Login") && hm.get("Login").equals("Yes")){
			objLogin.baseLogin("username", "password");	
		}
		else
			throw new SkipException("Run mode is No");
		}


	@Test (priority=2)
	public void add(){
		System.out.println("Test 2");
	}
	
	@Test (priority=3)
	public void sub(){
		System.out.println("Test 3");
	}
	
	@Test (priority=4)
	public void div(){
		System.out.println("Test 4");
		
	}
	
	
/*	@Test
	protected void finalize(){
		System.out.println("Deleting the memory");
	}
*/	
	
	/*@Test
	public void testVerify(){
		Assert.assertEquals("FaceBook", "FaceBook");
	}*/
	
	
	
	/*@Test
	public void sendMessage(){
		
		if(hm.containsKey("Send Message") && hm.get("Send Message").equals("Yes")){
			objLogin.sendMessageMethod();	
		}
		else
			throw new SkipException("Run mode is No");
		}*/
	
	@AfterMethod
	public void screenshot(ITestResult result) throws IOException{
		PrintStream myout = new PrintStream(new FileOutputStream(FileDescriptor.out));
		
		if(result.SUCCESS==result.getStatus()){
			myout.println("\033[42m Test Case--"+result.getName()+"-- is passed \033[0m");
			objScreenShots.takescreenshot(driver, result,"Pass");
			
		}
		
		else if(result.FAILURE==result.getStatus()){
			System.out.println("\033[41m Test Case--"+result.getName()+"-- is failed \033[0m");
			objScreenShots.takescreenshot(driver, result,"Fail");
			objUtility = new MailReport();
			objUtility.sendEmail(result);
				
		}
		
		
	}
	
	
	
	@AfterClass
	public void temp(){
		driver.manage().deleteAllCookies();
	}
	
	@AfterSuite
	public void quitDriver(){
		driver.quit();
		objUtility = new MailReport();
		objUtility.sendEmailReport();
		
	}

}


