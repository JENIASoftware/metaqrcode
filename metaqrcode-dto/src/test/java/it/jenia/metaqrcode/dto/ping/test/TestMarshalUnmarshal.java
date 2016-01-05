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
