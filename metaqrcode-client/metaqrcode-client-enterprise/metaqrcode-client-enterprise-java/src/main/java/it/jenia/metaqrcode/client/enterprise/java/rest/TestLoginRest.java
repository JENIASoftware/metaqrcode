package it.jenia.metaqrcode.client.enterprise.java.rest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.jenia.metaqrcode.enterprise.dto.login.RequestKeepAlive;
import it.jenia.metaqrcode.enterprise.dto.login.RequestLogin;
import it.jenia.metaqrcode.enterprise.dto.login.ResponseKeepAlive;
import it.jenia.metaqrcode.enterprise.dto.login.ResponseLogin;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLoginRest extends TestRestEnterprise {

	/**
	 *  to run this testcase you have to set 
	 *  metaqrcode.server.core.fakeRegistrationConfirmationCode=true
	 *  in configuration properties file
	 * @throws Exception
	 */
	@Test
	public void a_test() throws Exception {
		verifyRegistration();
		{
			RequestLogin req = new RequestLogin();
			req.setEmail(super.getUser());
			req.setPassword(super.getPassword());
			req.setClientId(super.getClientId());
			req.setClientSecret(super.getClientSecret());
			ResponseLogin res =  (ResponseLogin)doPostCallBasicXML("/rest/xml/login/enterprise/login", req, ResponseLogin.class);
			assert(0 == res.getReturnCode());
			sessionToken = res.getSessionToken();
		}
		{
			RequestKeepAlive req = new RequestKeepAlive();
			ResponseKeepAlive res =  (ResponseKeepAlive)doPostCallBasicXML("/rest/xml/login/enterprise/keepAlive", req, ResponseKeepAlive.class);
			assert(0 == res.getReturnCode());
		}
		{
			RequestKeepAlive req = new RequestKeepAlive();
			ResponseKeepAlive res =  (ResponseKeepAlive)doPostCallBasicXML("/rest/xml/login/enterprise/keepAlive", req, ResponseKeepAlive.class);
			assert(0 == res.getReturnCode());
		}
	}

}
