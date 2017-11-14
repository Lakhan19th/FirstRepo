package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestCaseReading {
	
	public HashMap hm = new HashMap();
	
	
	public void readTestCase() throws BiffException, IOException{
		FileInputStream fis = new FileInputStream("TestCase/Runmode.xls");
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sh = wb.getSheet(0);
		int row = sh.getRows();
		
		for(int i=1;i<row;i++){
			String testCase = sh.getCell(0, i).getContents();
			String runMode = sh.getCell(1, i).getContents();
			hm.put(testCase, runMode);
		}
	}
}
