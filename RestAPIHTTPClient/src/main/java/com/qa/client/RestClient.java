package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import com.qa.base.RestBase;

public class RestClient extends RestBase {
	HttpUriRequest httpUriRequest;

	public CloseableHttpResponse httpRequest(String url, String httpMethod, String entityString,
			HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		if (httpMethod.equalsIgnoreCase("GET")) {
			httpUriRequest = new HttpGet(url);
		} else if (httpMethod.equalsIgnoreCase("POST")) {
			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(entityString));
			httpUriRequest = post;
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			httpUriRequest = new HttpDelete(url);
		} else if (httpMethod.equalsIgnoreCase("PUT")) {
			HttpPut put = new HttpPut(url);
			put.setEntity(new StringEntity(entityString));
			httpUriRequest = put;
		} else if (httpMethod.equalsIgnoreCase("PATCH")) {
			HttpPatch patch = new HttpPatch(url);
			patch.setEntity(new StringEntity(entityString));
			httpUriRequest = patch;
		}

		else
			throw new RuntimeException("Failed to Execute the Request Method : " + httpMethod);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpUriRequest.addHeader(entry.getKey(), entry.getValue());
		}
		closeableHttpResponse = closeableHttpClient.execute(httpUriRequest);
		return closeableHttpResponse;
	}

}
