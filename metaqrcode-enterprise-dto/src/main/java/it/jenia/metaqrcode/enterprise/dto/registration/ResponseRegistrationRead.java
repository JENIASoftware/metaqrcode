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
package it.jenia.metaqrcode.enterprise.dto.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import it.jenia.metaqrcode.dto.Response;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * user information about currently logged in user
 * 
 * @author andreatessaro
 *
 */
public class ResponseRegistrationRead extends Response {
	
	@Getter
	@Setter
	/**
	 * user email
	 */
	private String email;

	@Getter
	@Setter
	/**
	 * user nickname
	 */
	private String nickName;

	@Getter
	@Setter
	/**
	 * user first name
	 */
	private String firstName;

	@Getter
	@Setter
	/**
	 * user last name
	 */
	private String lastName;

	@Getter
	@Setter
	/**
	 * user company name
	 */
	private String companyName;

	@Getter
	@Setter
	/**
	 * user address
	 */
	private String address;

	@Getter
	@Setter
	/**
	 * user city
	 */
	private String city;

	@Getter
	@Setter
	/**
	 * user zipcode
	 */
	private String zipCode;
	
	@Getter
	@Setter
	/**
	 * user preferred language
	 */
	private String preferredLanguage;

}
