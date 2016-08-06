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
package it.jenia.metaqrcode.enterprise.dto.login;

import it.jenia.metaqrcode.dto.Response;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
/**
 * each login create a user session.
 * each request that need authentication need to pass the sessionToken returned by the login service.
 * sessionToken is used (on server side) to understand wich is the user logged in.
 * sessionToken has a timeout, pleas check metaqrcode configuration parameter to understand the timeout (default 1 hour)
 * 
 * @author andreatessaro
 *
 */
public class ResponseLogin extends Response {
	
	@Getter
	@Setter
	/**
	 * the sessionToken created for this login request. default timeout : 1 hour
	 */
	private String sessionToken;

}
