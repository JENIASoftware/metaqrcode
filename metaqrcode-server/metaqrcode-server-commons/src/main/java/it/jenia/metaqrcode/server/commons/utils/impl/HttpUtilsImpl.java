package it.jenia.metaqrcode.server.commons.utils.impl;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import it.jenia.metaqrcode.dto.Response;
import it.jenia.metaqrcode.server.commons.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;

@Service(value="metaqrcode-server-commons-HttpUtilsImpl")
@Slf4j
public class HttpUtilsImpl implements HttpUtils {
	
	/**
	 * @see it.jenia.metaqrcode.server.commons.utils.HttpUtils#calculateStatusCode(Response response)
	 */
	@Override
	public void calculateStatusCode(Response response, ServletResponse servletResponse) {
		if (log.isDebugEnabled()) log.debug("Setting httpresponse status for returncode : " + response.getReturnCode());
		int status = 500; // default status
		if (response.getReturnCode()==0) {
			if (log.isDebugEnabled()) log.debug("Default status, returncode is 0 : OK");
			return;
		} else if (response.getReturnCode()<=-100000 && response.getReturnCode()>-101000) {
			status = 403;
		} else if (response.getReturnCode()<=-101000 && response.getReturnCode()>-102000) {
			status = 500;
		} else if (response.getReturnCode()<=-102000 && response.getReturnCode()>-103000) {
			status = 403;
		} else if (response.getReturnCode()<=-103000 && response.getReturnCode()>-104000) {
			status = 500;
		} else if (response.getReturnCode()<=-104000 && response.getReturnCode()>-105000) {
			status = 403;
		} else if (response.getReturnCode()<=-105000 && response.getReturnCode()>-106000) {
			status = 403;
		} else if (response.getReturnCode()<=-106000 && response.getReturnCode()>-107000) {
			status = 403;
		} else if (response.getReturnCode()<=-107000 && response.getReturnCode()>-108000) {
			status = 400;
		} else if (response.getReturnCode()<=-108000 && response.getReturnCode()>-109000) {
			status = 400;
		} else if (response.getReturnCode()<=-109000 && response.getReturnCode()>-110000) {
			status = 500;
		} else if (response.getReturnCode()<=-110000 && response.getReturnCode()>-111000) {
			status = 500;			
		} else if (response.getReturnCode()<=-111000 && response.getReturnCode()>-112000) {
			status = 500;			
		} else if (response.getReturnCode()<=-112000 && response.getReturnCode()>-113000) {
			status = 500;			
		} else if (response.getReturnCode()<=-113000 && response.getReturnCode()>-114000) {
			status = 500;			
		} else if (response.getReturnCode()<=-114000 && response.getReturnCode()>-115000) {
			status = 400;
		} else if (response.getReturnCode()<=-115000 && response.getReturnCode()>-116000) {
			status = 400;
		} else if (response.getReturnCode()<=-116000 && response.getReturnCode()>-117000) {
			status = 400;
		} else if (response.getReturnCode()<=-117000 && response.getReturnCode()>-118000) {
			status = 400;
		} else if (response.getReturnCode()<=-118000 && response.getReturnCode()>-119000) {
			status = 400;
		} else if (response.getReturnCode()<=-119000 && response.getReturnCode()>-120000) {
			status = 400;
		} else if (response.getReturnCode()<=-120000 && response.getReturnCode()>-121000) {
			status = 400;
		} else if (response.getReturnCode()<=-121000 && response.getReturnCode()>-122000) {
			status = 400;
		} else if (response.getReturnCode()<=-122000 && response.getReturnCode()>-123000) {
			status = 400;
		} else if (response.getReturnCode()<=-123000 && response.getReturnCode()>-124000) {
			status = 403;
		} else if (response.getReturnCode()<=-124000 && response.getReturnCode()>-125000) {
			status = 500;
		} else if (response.getReturnCode()<=-125000 && response.getReturnCode()>-126000) {
			status = 400;
		} else if (response.getReturnCode()<=-126000 && response.getReturnCode()>-127000) {
			status = 400;
		}
		if (log.isDebugEnabled()) log.debug("httpresponse status : " + status);
		((HttpServletResponse)servletResponse).setStatus(status);
	}
	
}
