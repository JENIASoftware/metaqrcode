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

import it.jenia.metaqrcode.dto.Dto;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
/**
 * this is a catalog entry (XSD)
 * 
 * @author andreatessaro
 *
 */
public class CatalogEntryDto extends Dto {

	@Getter
	@Setter
	/**
	 * catalog entry (XSD) id (the unique id of this XSD)
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * name of the catalog entry (XSD)
	 */
	private String name;

	@Getter
	@Setter
	/**
	 * description of the catalog entry (XSD)
	 */
	private String description;

	@Getter
	@Setter
	/**
	 * url of the catalog entry (XSD) to retrieve it on metaqrcode server
	 */
	private String catalogGet;

	@Getter
	@Setter
	/**
	 * list of catalog entry (XSD) that this repository entry (XML) is referring to
	 */
	private List<CatalogEntryDto> catalogEntries;

	@Getter
	@Setter
	/**
	 * nickname of the publisher of this catalog entry (XSD)
	 */
	private String nickNamePublisher;

	@Getter
	@Setter
	/**
	 * number of repository entry (XML) that refers to this catalog entry (XSD)
	 */
	private BigInteger numberOfReferences;

	@Getter
	@Setter
	/**
	 * number of stars (quality) assigned from the users to this catalog entry (XSD)
	 */
	private double stars;

}
