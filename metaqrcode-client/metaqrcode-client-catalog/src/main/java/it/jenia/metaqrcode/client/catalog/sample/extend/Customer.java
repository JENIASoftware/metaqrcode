package it.jenia.metaqrcode.client.catalog.sample.extend;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.client.catalog.sample.PersonData;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Customer extends PersonData {

	@XmlElement(nillable = false)
	private long customerId;

	@XmlElement(nillable = false)
	private CustomerType customerType;

	@XmlAnyElement(lax=true)
	private List<Object> any;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public List<Object> getAny() {
		return any;
	}

	public void setAny(List<Object> any) {
		this.any = any;
	}

}
