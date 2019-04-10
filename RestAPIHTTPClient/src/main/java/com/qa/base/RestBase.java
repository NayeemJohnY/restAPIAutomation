package com.qa.base;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;;

public class RestBase {
	
	public CloseableHttpClient closeableHttpClient;
	public CloseableHttpResponse closeableHttpResponse;
	public static String URL="https://reqres.in";
	public static String filepath=".\\src\\test\\java\\com\\qa\\data\\APITestData.xlsx";
	public static String sheetName="APITestDataSheet";
	public HashMap<String, String> headerMap;
	
	
	public RestBase() {
		// TODO Auto-generated constructor stub
		closeableHttpClient = HttpClients.createDefault();
		headerMap = new HashMap<String, String>();
	}

}
