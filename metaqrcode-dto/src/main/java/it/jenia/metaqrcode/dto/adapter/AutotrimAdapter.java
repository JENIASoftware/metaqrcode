package it.jenia.metaqrcode.dto.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter used for DTO attributes that need to be automatically trimmed
 * 
 * @author andreatessaro
 *
 */
public class AutotrimAdapter extends XmlAdapter<String, String> {

	public String unmarshal(String val) throws Exception {
        return val.trim();
    }
    public String marshal(String val) throws Exception {
		return val;
    }

}
