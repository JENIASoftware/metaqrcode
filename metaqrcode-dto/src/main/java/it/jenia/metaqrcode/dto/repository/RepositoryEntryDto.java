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

import it.jenia.metaqrcode.dto.Dto;
import it.jenia.metaqrcode.dto.catalog.CatalogEntryDto;
import it.jenia.metaqrcode.dto.link.RepositoryLinkDto;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
/**
 * this is a repository entry (XML)
 * 
 * @author andreatessaro
 *
 */
public class RepositoryEntryDto extends Dto {

	@Getter
	@Setter
	/**
	 * the id of the repository entry (XML)
	 */
	private BigInteger id;

	@Getter
	@Setter
	/**
	 * the user correlation id of this repository entry (XML)
	 */
	private String correlationId;

	@Getter
	@Setter
	/**
	 * list of catalog entry (XSD) that this repository entry (XML) is referring to
	 */
	private List<CatalogEntryDto> catalogEntries;

	@Getter
	@Setter
	/**
	 * list of repository link that this repository entry (XML) is referring to
	 */
	private List<RepositoryLinkDto> repositoryLInks;

	@Getter
	@Setter
	/**
	 * url of the repository entry (XML) to retrieve it on metaqrcode server
	 */
	private String repositoryGet;

	@Getter
	@Setter
	/**
	 * url of the image (PNG) of the qrcode of this repository entry (XML)
	 */
	private String qrcodeGet;

}
