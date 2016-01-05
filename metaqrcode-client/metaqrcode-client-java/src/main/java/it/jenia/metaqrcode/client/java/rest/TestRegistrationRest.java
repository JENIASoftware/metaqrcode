package it.jenia.metaqrcode.client.java.rest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.jenia.metaqrcode.dto.login.RequestLogin;
import it.jenia.metaqrcode.dto.login.ResponseLogin;
import it.jenia.metaqrcode.dto.registration.RequestPasswordForgotten;
import it.jenia.metaqrcode.dto.registration.RequestPasswordUpdate;
import it.jenia.metaqrcode.dto.registration.RequestRegistrationConfirm;
import it.jenia.metaqrcode.dto.registration.RequestRegistrationPrepare;
import it.jenia.metaqrcode.dto.registration.RequestRegistrationRead;
import it.jenia.metaqrcode.dto.registration.RequestRegistrationUpdate;
import it.jenia.metaqrcode.dto.registration.RequestUnregister;
import it.jenia.metaqrcode.dto.registration.ResponsePasswordForgotten;
import it.jenia.metaqrcode.dto.registration.ResponsePasswordUpdate;
import it.jenia.metaqrcode.dto.registration.ResponseRegistrationConfirm;
import it.jenia.metaqrcode.dto.registration.ResponseRegistrationPrepare;
import it.jenia.metaqrcode.dto.registration.ResponseRegistrationRead;
import it.jenia.metaqrcode.dto.registration.ResponseRegistrationUpdate;
import it.jenia.metaqrcode.dto.registration.ResponseUnregister;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistrationRest extends TestRest {

	protected static final String EMAIL = "bbb@bbb.bb";

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
			req.setEmail(EMAIL);
			req.setPassword(PASSWORD);
			req.setNickName("hhh");
			req.setFirstName("ccc");
			req.setLastName("ddd");
			req.setAddress("eee");
			req.setCity("fff");
			req.setZipCode("ggg");
			req.setPreferredLanguage("en");
			ResponseRegistrationPrepare res =  (ResponseRegistrationPrepare)doPostCallBasicXML("/rest/xml/registration/prepare", req, ResponseRegistrationPrepare.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration prepare ok ");
		}
		{
			RequestRegistrationConfirm req = new RequestRegistrationConfirm();
			req.setEmail(EMAIL);
			req.setRegistrationConfirmationCode("000000");
			ResponseRegistrationConfirm res =  (ResponseRegistrationConfirm)doPostCallBasicXML("/rest/xml/registration/confirm", req, ResponseRegistrationConfirm.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration confirm ok ");
		}
		{
			RequestLogin req = new RequestLogin();
			req.setEmail(EMAIL);
			req.setPassword(PASSWORD);
			ResponseLogin res =  (ResponseLogin)doPostCallBasicXML("/rest/xml/login/login", req, ResponseLogin.class);
			assert(0 == res.getReturnCode());
			sessionToken = res.getSessionToken();
		}
		{
			RequestRegistrationRead req = new RequestRegistrationRead();
			ResponseRegistrationRead res =  (ResponseRegistrationRead)doPostCallBasicXML("/rest/xml/registration/read", req, ResponseRegistrationRead.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration read ok ");
		}
		{
			RequestRegistrationUpdate req = new RequestRegistrationUpdate();
			req.setEmail(EMAIL);
			req.setNickName("hhh");
			req.setFirstName("ddd");
			req.setLastName("ccc");
			req.setAddress("eee");
			req.setCity("fff");
			req.setZipCode("ggg");
			req.setPreferredLanguage("en");
			ResponseRegistrationUpdate res =  (ResponseRegistrationUpdate)doPostCallBasicXML("/rest/xml/registration/update", req, ResponseRegistrationUpdate.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Registration update ok ");
		}
		{
			RequestPasswordUpdate req = new RequestPasswordUpdate();
			req.setPassword(PASSWORD);
			ResponsePasswordUpdate res =  (ResponsePasswordUpdate)doPostCallBasicXML("/rest/xml/registration/passwordUpdate", req, ResponsePasswordUpdate.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Password update ok ");
		}
		{
			RequestPasswordForgotten req = new RequestPasswordForgotten();
			ResponsePasswordForgotten res =  (ResponsePasswordForgotten)doPostCallBasicXML("/rest/xml/registration/passwordForgotten", req, ResponsePasswordForgotten.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Password forgotten ok ");
		}
		{
			RequestUnregister req = new RequestUnregister();
			req.setEmail(EMAIL);
			ResponseUnregister res =  (ResponseUnregister)doPostCallBasicXML("/rest/xml/registration/remove", req, ResponseUnregister.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Unregister ok ");
		}
	}

}
