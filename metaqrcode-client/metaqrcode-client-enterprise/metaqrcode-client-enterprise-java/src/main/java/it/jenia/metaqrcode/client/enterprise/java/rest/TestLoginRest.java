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
