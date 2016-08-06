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
package it.jenia.metaqrcode.dto.catalog;

import it.jenia.metaqrcode.dto.Response;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * result of the upload request.
 * 
 * @author andreatessaro
 *
 */
public class ResponseCatalogUpload extends Response {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XSD) assigned from the metaqrcode server
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * the URL to be used to refer (http get) the catalog entry (XSD)
	 */
	private String catalogGet;

}
