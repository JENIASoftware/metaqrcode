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

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import it.jenia.metaqrcode.dto.RequestSearch;
import it.jenia.metaqrcode.dto.adapter.AutotrimAdapter;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * search for a previously uploaded catalog entry (XSD)
 * 
 * @author andreatessaro
 *
 */
public class RequestCatalogSearch extends RequestSearch {
	
	@Getter
	@Setter
	/**
	 * the id of the catalog entry (XSD) to be searched for (optional)
	 */
	private BigInteger id;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * like expression for the name of the catalog entry (XSD) to be searched for
	 */
	private String nameLike;

	@Getter
	@Setter
	@XmlJavaTypeAdapter(AutotrimAdapter.class)
	/**
	 * like expression for the description of the catalog entry (XSD) to be searched for
	 */
	private String descriptionLike;

	@Getter
	@Setter
	/**
	 * search only for mine catalog entry (XSD). i am the uploader
	 */
	private boolean onlyMine;

}
