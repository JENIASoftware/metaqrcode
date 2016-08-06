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
package it.jenia.metaqrcode.dto.ping.test;

import it.jenia.metaqrcode.dto.ping.RequestPingAnonymous;

import javax.xml.bind.JAXBException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMarshalUnmarshal {

	@Test
	public void a_testJson() throws JAXBException {
		RequestPingAnonymous reqM = new RequestPingAnonymous();
		reqM.setFrom("from");
		String jsonStr1 = reqM.toJson();
		System.out.println(jsonStr1);
		RequestPingAnonymous reqU = (RequestPingAnonymous)RequestPingAnonymous.fromJson(jsonStr1, RequestPingAnonymous.class);
		assert(reqU.getFrom().equals(reqM.getFrom()));
		String jsonStr2 = reqU.toJson();
		System.out.println(jsonStr2);
		assert(jsonStr1.equals(jsonStr2));
	}

	@Test
	public void b_testXML() throws JAXBException {
		RequestPingAnonymous reqM = new RequestPingAnonymous();
		reqM.setFrom("from");
		String xmlStr1 = reqM.toXML();
		System.out.println(xmlStr1);
		RequestPingAnonymous reqU = (RequestPingAnonymous)RequestPingAnonymous.fromXML(xmlStr1, RequestPingAnonymous.class);
		assert(reqU.getFrom().equals(reqM.getFrom()));
		String xmlStr2 = reqU.toXML();
		System.out.println(xmlStr2);
		assert(xmlStr1.equals(xmlStr2));
	}

}
