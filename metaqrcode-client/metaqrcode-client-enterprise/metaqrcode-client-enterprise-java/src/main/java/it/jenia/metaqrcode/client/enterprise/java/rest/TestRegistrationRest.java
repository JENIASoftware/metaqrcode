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

import it.jenia.metaqrcode.enterprise.dto.login.RequestLogin;
import it.jenia.metaqrcode.enterprise.dto.login.ResponseLogin;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestPasswordForgotten;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestPasswordUpdate;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestRegistrationConfirm;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestRegistrationPrepare;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestRegistrationRead;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestRegistrationUpdate;
import it.jenia.metaqrcode.enterprise.dto.registration.RequestUnregister;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponsePasswordForgotten;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponsePasswordUpdate;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseRegistrationConfirm;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseRegistrationPrepare;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseRegistrationRead;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseRegistrationUpdate;
import it.jenia.metaqrcode.enterprise.dto.registration.ResponseUnregister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistrationRest extends TestRestEnterprise {

	/**
	 *  to run this testcase you have to set 
	 *  metaqrcode.server.core.fakeRegistrationConfirmationCode=true
	 *  in configuration properties file
	 * @throws Exception
	 */
	@Test
	public void a_test() throws Exception {
		{
			RequestRegistrationPrepare req = new RequestRegistrationPrepare();
			req.setEmail(super.getUser());
			req.setPassword(super.getPassword());
			req.setNickName(super.getNickName());
			req.setFirstName(super.getUser());
			req.setLastName(super.getUser());
			req.setAddress(super.getUser());
			req.setCity(super.getUser());
			req.setCompanyName(super.getUser());
			req.setZipCode(super.getUser());
			req.setPreferredLanguage("en");
			ResponseRegistrationPrepare res =  (ResponseRegistrationPrepare)doPostCallBasicXML("/rest/xml/registration/enterprise/prepare", req, ResponseRegistrationPrepare.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration prepare ok ");
		}
		{
			RequestRegistrationConfirm req = new RequestRegistrationConfirm();
			req.setEmail(super.getUser());
			req.setRegistrationConfirmationCode("000000");
			ResponseRegistrationConfirm res =  (ResponseRegistrationConfirm)doPostCallBasicXML("/rest/xml/registration/enterprise/confirm", req, ResponseRegistrationConfirm.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration confirm ok ");
		}
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
			RequestRegistrationRead req = new RequestRegistrationRead();
			ResponseRegistrationRead res =  (ResponseRegistrationRead)doPostCallBasicXML("/rest/xml/registration/enterprise/read", req, ResponseRegistrationRead.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration read ok ");
		}
		{
			RequestRegistrationUpdate req = new RequestRegistrationUpdate();
			req.setEmail(super.getUser());
			req.setNickName(super.getNickName());
			req.setFirstName(super.getUser());
			req.setLastName(super.getUser());
			req.setAddress(super.getUser());
			req.setCity(super.getUser());
			req.setCompanyName(super.getUser());
			req.setZipCode(super.getUser());
			req.setPreferredLanguage("en");
			ResponseRegistrationUpdate res =  (ResponseRegistrationUpdate)doPostCallBasicXML("/rest/xml/registration/enterprise/update", req, ResponseRegistrationUpdate.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration update ok ");
		}
		{
			RequestPasswordUpdate req = new RequestPasswordUpdate();
			req.setPassword(super.getPassword());
			ResponsePasswordUpdate res =  (ResponsePasswordUpdate)doPostCallBasicXML("/rest/xml/registration/enterprise/passwordUpdate", req, ResponsePasswordUpdate.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Password update ok ");
		}
		{
			RequestPasswordForgotten req = new RequestPasswordForgotten();
			ResponsePasswordForgotten res =  (ResponsePasswordForgotten)doPostCallBasicXML("/rest/xml/registration/enterprise/passwordForgotten", req, ResponsePasswordForgotten.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Password forgotten ok ");
		}
		{
			RequestUnregister req = new RequestUnregister();
			req.setEmail(super.getUser());
			ResponseUnregister res =  (ResponseUnregister)doPostCallBasicXML("/rest/xml/registration/enterprise/remove", req, ResponseUnregister.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Unregister ok ");
		}
	}

}
