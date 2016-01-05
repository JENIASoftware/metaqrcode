package it.jenia.metaqrcode.client.java.rest;

import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import it.jenia.metaqrcode.dto.Request;
import it.jenia.metaqrcode.dto.Response;
import it.jenia.metaqrcode.dto.login.RequestLogin;
import it.jenia.metaqrcode.dto.login.ResponseLogin;
import it.jenia.metaqrcode.dto.registration.RequestRegistrationConfirm;
import it.jenia.metaqrcode.dto.registration.RequestRegistrationPrepare;
import it.jenia.metaqrcode.dto.registration.ResponseRegistrationConfirm;
import it.jenia.metaqrcode.dto.registration.ResponseRegistrationPrepare;

public abstract class TestRest {
	
	protected static final String PASSWORD = "bbbbbb";
	protected static final String EMAIL = "pippo@jenia.it";
	protected String host;
	protected int port;
	protected String user;
	protected String password;
	protected String scheme;
	protected String contextRoot;
	protected String sessionToken;
	protected boolean registrationPrepareSuccess;
	protected boolean registrationConfirmSuccess;
	protected boolean loginSuccess;

	public TestRest() {
		super();
		host = "localhost";
		port = 8080;
		user = "dummy";
		password = "disabled";
		scheme = "http";
		contextRoot = "/api";
	}
	
	protected void verifyRegistrationAndLogin() throws Exception {
		registrationPrepare();
		if (registrationPrepareSuccess) {
			registrationConfirm();
			if (registrationConfirmSuccess) {
				login();
			}
		}
	}

	protected void verifyRegistration() throws Exception {
		registrationPrepare();
		if (registrationPrepareSuccess) {
			registrationConfirm();
		}
	}
	
	protected void registrationPrepare() throws Exception {
		RequestRegistrationPrepare req = new RequestRegistrationPrepare();
		req.setEmail(EMAIL);
		req.setPassword(PASSWORD);
		req.setNickName("iiii");
		req.setFirstName("cccc");
		req.setLastName("dddd");
		req.setAddress("eeee");
		req.setCity("ffff");
		req.setZipCode("gggg");
		req.setPreferredLanguage("it");
		ResponseRegistrationPrepare res =  (ResponseRegistrationPrepare)doPostCallBasicXML("/rest/xml/registration/prepare", req, ResponseRegistrationPrepare.class);
		if (res.getReturnCode()==0 || res.getReason().indexOf("email already configured")!=-1) {
			registrationPrepareSuccess=true;
		} else {
			assert(0 == res.getReturnCode());
		}
	}

	private void registrationConfirm() throws Exception {
		RequestRegistrationConfirm req = new RequestRegistrationConfirm();
		req.setEmail(EMAIL);
		req.setRegistrationConfirmationCode("000000");
		ResponseRegistrationConfirm res =  (ResponseRegistrationConfirm)doPostCallBasicXML("/rest/xml/registration/confirm", req, ResponseRegistrationConfirm.class);
		assert(0 == res.getReturnCode());
		registrationConfirmSuccess=true;
	}

	private void login() throws Exception {
		RequestLogin req = new RequestLogin();
		req.setEmail(EMAIL);
		req.setPassword(PASSWORD);
		ResponseLogin res =  (ResponseLogin)doPostCallBasicXML("/rest/xml/login/login", req, ResponseLogin.class);
		assert(0 == res.getReturnCode());
		sessionToken = res.getSessionToken();
		loginSuccess=true;
	}

	@SuppressWarnings("static-access")
	protected Response doPostCallBasicXML(String url, Request reqObj, Class<? extends Response> respClass) throws Exception {
		
		HttpHost targetHost = new HttpHost(host, port, scheme);
		
		HttpClientBuilder httpclientBuilder = HttpClientBuilder.create();

		CloseableHttpClient httpclient = httpclientBuilder.build();
        HttpPost httppost = new HttpPost(contextRoot+url);
        if (sessionToken!=null) {
        	httppost.addHeader("Authorization", "Token "+sessionToken);
        }
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
        if (sessionToken!=null) {
        	httppost.addHeader("Authorization", "Token "+sessionToken);
        }
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
        if (sessionToken!=null) {
        	httppost.addHeader("Authorization", "Token "+sessionToken);
        }
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
        if (sessionToken!=null) {
        	httppost.addHeader("Authorization", "Token "+sessionToken);
        }
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
        if (sessionToken!=null) {
        	httppost.addHeader("Authorization", "Token "+sessionToken);
        }
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
        if (sessionToken!=null) {
        	httppost.addHeader("Authorization", "Token "+sessionToken);
        }
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
        if (sessionToken!=null) {
        	httpget.addHeader("Authorization", "Token "+sessionToken);
        }
		HttpResponse response = httpclient.execute(targetHost, httpget);
		
		HttpEntity entityResp = response.getEntity();

        InputStream is = entityResp.getContent();
        
        byte[] ret = IOUtils.toByteArray(is);
        
        EntityUtils.consume(entityResp);
        
        httpclient.close();
        
        return ret;

	}
	

}
