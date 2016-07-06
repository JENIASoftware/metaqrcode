package it.jenia.metaqrcode.client.java.rest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;
import it.jenia.metaqrcode.dto.ping.ResponsePing;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPingRest extends TestRest {

	private static final String FROM = "ping requestor";

	@Test
	public void a_test() throws Exception {
		{
			RequestPingAnonymous req = new RequestPingAnonymous();
			req.setFrom(FROM);
			ResponsePing res =  (ResponsePing)doPostCallBasicJSON("/rest/json/ping/nowAnonymous", req, ResponsePing.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Ping ok JSON");
		}
	}
	@Test
	public void b_test() throws Exception {
		{
			RequestPingAnonymous req = new RequestPingAnonymous();
			req.setFrom(FROM);
			ResponsePing res =  (ResponsePing)doPostCallBasicJSON("/rest/json/ping/failAnonymous", req, ResponsePing.class);
			assert(-1 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Fail ok JSON");
		}
	}
	@Test
	public void c_test() throws Exception {
		{
			RequestPingAnonymous req = new RequestPingAnonymous();
			req.setFrom(FROM);
			ResponsePing res =  (ResponsePing)doPostCallBasicXML("/rest/xml/ping/nowAnonymous", req, ResponsePing.class);
			assert(0 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Ping ok XML");
		}
	}
	@Test
	public void d_test() throws Exception {
		{
			RequestPingAnonymous req = new RequestPingAnonymous();
			req.setFrom(FROM);
			ResponsePing res =  (ResponsePing)doPostCallBasicXML("/rest/xml/ping/failAnonymous", req, ResponsePing.class);
			assert(-1 == res.getReturnCode());
			System.out.println("+++++++++++++++++++++++++Fail ok XML");
		}
	}

}
