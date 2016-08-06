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
package it.jenia.metaqrcode.dto.repository;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.jenia.metaqrcode.dto.Request;
import it.jenia.metaqrcode.dto.adapter.AutotrimAdapter;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * request to update a previously generated catalog entry (XML)   
 * 
 * @author andreatessaro
 *
 */
public class RequestRepositoryUpdate extends Request {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XML) to be updated
	 */
	private BigInteger id;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * the new default catalog entry (XSD) that this repository entry (XML) is referring to 
	 */
	private String defaultCatalog;

	@Getter
	@Setter
	/** you can specify if this repository entry (XML) is private (visible only to you).
	 * Metaqrcode server will bind it to the repository entry (XML) for further processing
	 */
	private boolean personal;

}
