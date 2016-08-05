package it.jenia.metaqrcode.server.authentication.util;

import java.net.URL;

import org.junit.Test;

public class URLTest {
	@Test
	public void test() throws Exception {
		{
			URL issuerURL = new URL("http://www.metaqrcode.com/oidc");
			URL openidconnectIssuerURL = new URL("http://www.metaqrcode.com/oidc");
	        boolean check = checkUrls(issuerURL, openidconnectIssuerURL);
	        System.out.println("" + !check + " must be true");
		}
		{
			URL issuerURL = new URL("http://www.metaqrcode.com:80/oidc");
			URL openidconnectIssuerURL = new URL("http://www.metaqrcode.com/oidc");
	        boolean check = checkUrls(issuerURL, openidconnectIssuerURL);
	        System.out.println("" + !check + " must be true");
		}
		{
			URL issuerURL = new URL("http://www.metaqrcode.com:80/oidc/");
			URL openidconnectIssuerURL = new URL("http://www.metaqrcode.com/oidc");
	        boolean check = checkUrls(issuerURL, openidconnectIssuerURL);
	        System.out.println("" + !check + " must be true");
		}
		{
			URL issuerURL = new URL("http://www.metaqrcode.it:80/oidc/");
			URL openidconnectIssuerURL = new URL("http://www.metaqrcode.com/oidc");
	        boolean check = checkUrls(issuerURL, openidconnectIssuerURL);
	        System.out.println("" + !check + " must be false");
		}
		{
			URL issuerURL = new URL("http://www.metaqrcode.it:80/oidc?a=a");
			URL openidconnectIssuerURL = new URL("http://www.metaqrcode.com/oidc");
	        boolean check = checkUrls(issuerURL, openidconnectIssuerURL);
	        System.out.println("" + !check + " must be false");
		}
		{
			URL issuerURL = new URL("http://www.metaqrcode.it:80/oidc?a=a");
			URL openidconnectIssuerURL = new URL("http://www.metaqrcode.com/oidc");
	        boolean check = checkUrls(issuerURL, openidconnectIssuerURL);
	        System.out.println("" + !check + " must be false");
		}
	}

	private boolean checkUrls(URL issuerURL, URL openidconnectIssuerURL) {
		return !issuerURL.getProtocol().equals(openidconnectIssuerURL.getProtocol()) ||
		!issuerURL.getHost().equals(openidconnectIssuerURL.getHost()) ||
		(issuerURL.getPort()==-1?issuerURL.getDefaultPort():issuerURL.getPort())!=(openidconnectIssuerURL.getPort()==-1?openidconnectIssuerURL.getDefaultPort():openidconnectIssuerURL.getPort()) ||
		!issuerURL.getPath().equals(openidconnectIssuerURL.getPath()) ||
		(issuerURL.getQuery()!=null && 
		!issuerURL.getQuery().equals(openidconnectIssuerURL.getQuery())) ||
		(openidconnectIssuerURL.getQuery()!=null && 
		!openidconnectIssuerURL.getQuery().equals(issuerURL.getQuery()));
	}
}