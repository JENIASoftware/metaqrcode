package it.jenia.metaqrcode.client.enterprise.java.rest;

import org.apache.http.client.methods.HttpRequestBase;

import it.jenia.metaqrcode.client.java.rest.TestRest;
import it.jenia.metaqrcode.enterprise.dto.login.RequestLogin;
import it.jenia.metaqrcode.enterprise.dto.login.ResponseLogin;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestRegistrationConfirm;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestRegistrationPrepare;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseRegistrationConfirm;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseRegistrationPrepare;

public abstract class TestRestEnterprise extends TestRest {
	
	protected String sessionToken;
	protected String clientId;
	protected String clientSecret;
	protected boolean registrationPrepareSuccess;
	protected boolean registrationConfirmSuccess;
	protected boolean loginSuccess;
	
	private static String user;
	private static String password;
	private static String nickName;
	
	static {
		user = "test_"+System.currentTimeMillis()+"@jenia.it";
		password = user;
		nickName = password;
	}

	public TestRestEnterprise() {
		super();
		clientId = "b79eaae3-062d-4466-a10f-ffdb935bf7a1";
		clientSecret = "Ghk6MiK8F6pRUaoJUxCtioktP3tYkqLxewqmB-YtrEZAKYmadZ2oiHWQmHsaE7mbf5u-n-CKjbNeg3lQAXL6XQ";
	}

	@Override
	protected void completeHttpRequest(HttpRequestBase request) {
        if (sessionToken!=null) {
        	request.addHeader("Authorization", "Token "+sessionToken);
        }
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
		req.setEmail(user);
		req.setPassword(password);
		req.setNickName(nickName);
		req.setFirstName(user);
		req.setLastName(user);
		req.setAddress(user);
		req.setCity(user);
		req.setZipCode(user);
		req.setCompanyName(user);
		req.setPreferredLanguage("it");
		ResponseRegistrationPrepare res =  (ResponseRegistrationPrepare)doPostCallBasicXML("/rest/xml/registration/enterprise/prepare", req, ResponseRegistrationPrepare.class);
		if (res.getReturnCode()==0 || res.getReason().indexOf("email already configured")!=-1) {
			registrationPrepareSuccess=true;
		} else {
			assert(0 == res.getReturnCode());
		}
	}

	private void registrationConfirm() throws Exception {
		RequestRegistrationConfirm req = new RequestRegistrationConfirm();
		req.setEmail(user);
		req.setRegistrationConfirmationCode("000000");
		ResponseRegistrationConfirm res =  (ResponseRegistrationConfirm)doPostCallBasicXML("/rest/xml/registration/enterprise/confirm", req, ResponseRegistrationConfirm.class);
		assert(0 == res.getReturnCode());
		registrationConfirmSuccess=true;
	}

	private void login() throws Exception {
		RequestLogin req = new RequestLogin();
		req.setEmail(user);
		req.setPassword(password);
		req.setClientId(clientId);
		req.setClientSecret(clientSecret);
		ResponseLogin res =  (ResponseLogin)doPostCallBasicXML("/rest/xml/login/enterprise/login", req, ResponseLogin.class);
		assert(0 == res.getReturnCode());
		sessionToken = res.getSessionToken();
		loginSuccess=true;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		TestRestEnterprise.user = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		TestRestEnterprise.password = password;
	}

	public static String getNickName() {
		return nickName;
	}

	public static void setNickName(String nickName) {
		TestRestEnterprise.nickName = nickName;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}
}
