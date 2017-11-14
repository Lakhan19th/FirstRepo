package com.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
public class ScreenShots {
	
	
	public void takescreenshot(WebDriver driver,ITestResult screenshotName, String location) throws IOException
	{
	String name = screenshotName.getName();	
	File screenshot = new File("Screenshots\\"+location+"\\"+name+ ".png");
	File tmpScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(tmpScreenshot, screenshot);
	}

}
