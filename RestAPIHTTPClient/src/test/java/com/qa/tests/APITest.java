package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import com.qa.base.RestBase;
import com.qa.client.RestClient;
import com.qa.testUtils.ExcelAPI;


public class APITest extends RestBase {

	RestClient rst;
	public int rowCount=1;
	public ExcelAPI excelAPI;
	StatusLine statusLine;
	int statusCode;

	@BeforeMethod
	public void setupAPI() {
		rst = new RestClient();
		excelAPI= new ExcelAPI();
	}

	@Test(dataProvider = "APITestData")
	public void test(String httpMethod,String serviceUrl, int expectedStatusCode)
			throws ClientProtocolException, IOException {
		rowCount++;
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		JSONObject requestJson = new JSONObject();
		requestJson.put("name", "Naveen2");
		requestJson.put("job", "tutor");
		requestJson.put("email", "peter@klaven");
		closeableHttpResponse = rst.httpRequest(URL+serviceUrl, httpMethod, requestJson.toString(),headerMap);
		statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		statusLine=closeableHttpResponse.getStatusLine();
		//String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		//System.out.println(responseString);	
		excelAPI.writeCelldata(rowCount, "StatusLine", statusLine.toString());
		excelAPI.writeCelldata(rowCount, "ActualStatusCode", statusCode);
		//excelAPI.writeCelldata(rowCount, "ResponseString", responseString);
		Assert.assertEquals(statusCode, expectedStatusCode);	
		
	}
	
	

	@DataProvider(name = "APITestData")
	public Object[][] testdata() {
		excelAPI = new ExcelAPI();
		return excelAPI.getAllCelldata();
	}

}
