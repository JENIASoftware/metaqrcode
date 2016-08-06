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
