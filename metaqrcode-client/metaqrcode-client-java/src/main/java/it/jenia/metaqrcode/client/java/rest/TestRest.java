/*
 * Copyright 2016 JENIA Software.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.jenia.metaqrcode.client.java.rest;

import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import it.jenia.metaqrcode.dto.Request;
import it.jenia.metaqrcode.dto.Response;

public abstract class TestRest {
	
	protected String host;
	protected int port;
	protected String scheme;
	protected String contextRoot;

	public TestRest() {
		super();
		host = "localhost";
		port = 8080;
		scheme = "http";
		contextRoot = "/api";
	}
	
	@SuppressWarnings("static-access")
	protected Response doPostCallBasicXML(String url, Request reqObj, Class<? extends Response> respClass) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();

		CloseableHttpClient httpclient = httpclientBuilder.build();
        HttpPost httppost = new HttpPost(contextRoot+url);
        completeHttpRequest(httppost);
        String req = reqObj.toXML();
        ByteArrayEntity body = new ByteArrayEntity(req.getBytes());
        body.setContentType("application/xml");
        httppost.setEntity(body);
		HttpResponse response = httpclient.execute(targetHost, httppost);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        Response ret = respClass.newInstance();
        ret = (Response) ret.fromXML(is, respClass);
        if (ret.getReturnCode()!=0) {
        	System.out.println("returnCode = " + ret.getReturnCode() + ", reason : " + ret.getReason());
        }
        
        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}
	
	@SuppressWarnings("static-access")
	protected Response doPostCallWithAttachmentXML(String url, Request reqObj, String fileParameterName, byte[] file, Class<? extends Response> respClass) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpclientBuilder.build();
		
        HttpPost httppost = new HttpPost(contextRoot+url);
        completeHttpRequest(httppost);
        String request = reqObj.toXML();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addTextBody("request", request, ContentType.APPLICATION_XML);
        multipartEntityBuilder.addBinaryBody(fileParameterName, file, ContentType.APPLICATION_XML, null);
        httppost.setEntity(multipartEntityBuilder.build());
		HttpResponse response = httpclient.execute(targetHost, httppost);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        Response ret = respClass.newInstance();
        ret = (Response) ret.fromXML(is, respClass);
        
        if (ret.getReturnCode()!=0) {
        	System.out.println("returnCode = " + ret.getReturnCode() + ", reason : " + ret.getReason());
        }

        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}

	protected byte[] doPostCallReturningResourceXML(String url, Request reqObj) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpclientBuilder.build();
		
        HttpPost httppost = new HttpPost(contextRoot+url);
        completeHttpRequest(httppost);
        String req = reqObj.toXML();
        ByteArrayEntity body = new ByteArrayEntity(req.getBytes());
        body.setContentType("application/xml");
        httppost.setEntity(body);
		HttpResponse response = httpclient.execute(targetHost, httppost);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        byte[] ret = IOUtils.toByteArray(is);
        
        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}
	
	@SuppressWarnings("static-access")
	protected Response doPostCallBasicJSON(String url, Request reqObj, Class<? extends Response> respClass) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpclientBuilder.build();
		
        HttpPost httppost = new HttpPost(contextRoot+url);
        completeHttpRequest(httppost);
        String req = reqObj.toJson();
        ByteArrayEntity body = new ByteArrayEntity(req.getBytes());
        body.setContentType("application/json; charset=utf-8");
        httppost.setEntity(body);
		HttpResponse response = httpclient.execute(targetHost, httppost);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        Response ret = respClass.newInstance();
        ret = (Response) ret.fromJson(is, respClass);

        if (ret.getReturnCode()!=0) {
        	System.out.println("returnCode = " + ret.getReturnCode() + ", reason : " + ret.getReason());
        }
        
        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}
	
	@SuppressWarnings("static-access")
	protected Response doPostCallWithAttachmentJSON(String url, Request reqObj, String fileParameterName, byte[] file, Class<? extends Response> respClass) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpclientBuilder.build();
		
        HttpPost httppost = new HttpPost(contextRoot+url);
        completeHttpRequest(httppost);
        String request = reqObj.toJson();
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addTextBody("request", request, ContentType.APPLICATION_JSON);
        multipartEntityBuilder.addBinaryBody(fileParameterName, file, ContentType.APPLICATION_XML, null);
        httppost.setEntity(multipartEntityBuilder.build());
		HttpResponse response = httpclient.execute(targetHost, httppost);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        Response ret = respClass.newInstance();
        ret = (Response) ret.fromJson(is, respClass);
        
        if (ret.getReturnCode()!=0) {
        	System.out.println("returnCode = " + ret.getReturnCode() + ", reason : " + ret.getReason());
        }

        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}

	protected byte[] doPostCallReturningResourceJSON(String url, Request reqObj) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpclientBuilder.build();
		
        HttpPost httppost = new HttpPost(contextRoot+url);
        completeHttpRequest(httppost);
        String req = reqObj.toJson();
        ByteArrayEntity body = new ByteArrayEntity(req.getBytes());
        body.setContentType("application/json; charset=utf-8");
        httppost.setEntity(body);
		HttpResponse response = httpclient.execute(targetHost, httppost);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        byte[] ret = IOUtils.toByteArray(is);
        
        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}
	
	protected byte[] doGetCallReturningResource(String url) throws Exception {
		
		URI uri = new URI(url);
		
		HttpHost targetHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();
		CloseableHttpClient httpclient = httpclientBuilder.build();
		
        HttpGet httpget = new HttpGet(uri.getPath());
        completeHttpRequest(httpget);
		HttpResponse response = httpclient.execute(targetHost, httpget);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        byte[] ret = IOUtils.toByteArray(is);
        
        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}
	
	protected void completeHttpRequest(HttpRequestBase request) {
		// this method will be empty. used only ofr enterprise calls
	}
	

}
