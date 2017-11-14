package com.qa.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	WebDriver driver;
	
	public Login(WebDriver driver){
		this.driver = driver;
	}
	
	
	
	By USERNAME = By.xpath(".//*[@id='emai']");
	By PASSWORD = By.xpath(".//*[@id='pass']");
	By LOGIN = By.xpath("//input[@value='Log In']");
	
	
	
	public void sendUserName(String username){
		//long starttime = System.currentTimeMillis();
		driver.findElement(USERNAME).sendKeys(username);
		//long endtime = System.currentTimeMillis();
		
		//long finalTime = endtime-starttime;
	}
	
	
	public void sendPassword(String password){
		driver.findElement(PASSWORD).sendKeys(password);
	}
	
	
	public void clickLogin(){
		driver.findElement(LOGIN).click();
	}
	
	

	
	public void baseLogin(String username, String password){
		this.sendUserName(username);
		this.sendPassword(password);
		this.clickLogin();
	}
	
	
	
	
	
	
}
