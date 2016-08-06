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
package it.jenia.metaqrcode.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Generic response.
 * All response returned by metaqrcode MUST extends this response
 * 
 * @author andreatessaro
 *
 */
public class Response extends Dto {
	
	private static final int SUCCESS_RETURN_CODE = 0;

	private static final String SUCCESS = "success";

	@Getter
	@Setter
	/**
	 * if different then zero, the service is KO
	 */
	private int returnCode;

	@Getter
	@Setter
	/**
	 * if returnCode is different then zero, this is the reason of the return code
	 */
	private String reason;

	public Response() {
		super();
		returnCode = SUCCESS_RETURN_CODE;
		reason = SUCCESS;
	}

}
