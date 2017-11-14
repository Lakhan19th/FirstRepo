package com.qa.example;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Example {

	
	@BeforeGroups(groups="test2")
	public void testgroup1(){
		System.out.println("Group 1");
	}
	
	@AfterGroups
	public void testgroup2(){
		System.out.println("Group 2");
	}
	

	@Test(groups = {"test2"})
	public void test1(){
		System.out.println("Test 1");
	}
		
	
	@Test(groups = {"test2"})
	public void test2(){
		System.out.println("Test 2");
	}
	
	
	@Test(groups = {"test2"})
	public void test3(){
		System.out.println("Test 3");
	}
	
	@Test
	public void test4(){
		System.out.println("Test 4");
	}
	
	@Test
	public void test5(){
		System.out.println("Test 5");
	}
}
