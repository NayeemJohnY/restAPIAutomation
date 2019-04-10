package com.qa.testUtils;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.tests.APITest;

public class TestListener implements ITestListener {
	XSSFCell cell;
	APITest apiTest;
	

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		apiTest = (APITest) result.getInstance();
		cell = apiTest.excelAPI.writeCelldata(apiTest.rowCount, "TestResult", "PASS");
		apiTest.excelAPI.setupCell(cell, HSSFColorPredefined.GREEN.getIndex());
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		apiTest = (APITest) result.getInstance();
		cell =apiTest.excelAPI.writeCelldata(apiTest.rowCount, "TestResult", "FAIL");
		apiTest.excelAPI.setupCell(cell, HSSFColorPredefined.RED.getIndex());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		apiTest = (APITest) result.getInstance();
		cell =apiTest.excelAPI.writeCelldata(apiTest.rowCount, "TestResult", "FAIL");
		apiTest.excelAPI.setupCell(cell, HSSFColorPredefined.YELLOW.getIndex());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
