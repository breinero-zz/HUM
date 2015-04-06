package com.bryanreinero.hum.server;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Response {
	private String redirectURL = null;
	private String responseBody = null;
	private int responseStatus = HttpServletResponse.SC_OK;
	private HashMap<String, String> headers = null;
	private HashMap<String, Cookie> cookies = null;
	private String characterEncoding = null;
	private Integer contentLength    = null;
	private String contentType       = null;
	private String locale            = null;
	
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}
	public String getRedirectURL() {
		return redirectURL;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseStatus(int code) {
		responseStatus = code;
	}
	
	public void setResponseHeader(String name, String value) {
		if(headers == null)
			headers = new HashMap<String, String>();
		headers.put(name, value);
	}
	
	public void setCookie(String name, Cookie value) {
		if(cookies == null)
			cookies = new HashMap<String, Cookie>();
		
		cookies.put(name, value);
	}
	
	public void setContentType(String contentType){
		this.contentType = contentType;
	}
	
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
	
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}
	public void setContentLength(String contentLength) {
		this.contentLength = Integer.parseInt(contentLength);
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public int getResponseStatus() {
		return responseStatus;
	}
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public HashMap<String, Cookie> getCookies() {
		return cookies;
	}
	public String getCharacterEncoding() {
		return characterEncoding;
	}
	public Integer getContentLength() {
		return contentLength;
	}
	public String getContentType() {
		return contentType;
	}
	public String getLocale() {
		return locale;
	}
	
}
