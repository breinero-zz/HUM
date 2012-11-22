package com.bryanreinero.hum.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Responder {
	public static void respond(HttpServletResponse response, Response responseObj) throws IOException{
		if(responseObj.getRedirectURL() != null){
			response.sendRedirect(responseObj.getRedirectURL());
			return;
		}
		
		if(responseObj.getCharacterEncoding() != null)
			response.setCharacterEncoding(responseObj.getCharacterEncoding());
		
		if(responseObj.getContentLength() != null)
			response.setContentLength(responseObj.getContentLength());
		
		if(responseObj.getContentType() != null)
			response.setContentType(responseObj.getContentType());

		if(responseObj.getCookies() != null){
			Iterator<Entry<String, Cookie>> iterator =  responseObj.getCookies().entrySet().iterator();
			while(iterator.hasNext()){
				Entry <String, Cookie>entry = iterator.next();
				response.addCookie(entry.getValue());
			}
		}
		
		if(responseObj.getHeaders() != null){
			Iterator<Entry<String, String>> iterator =  responseObj.getHeaders().entrySet().iterator();
			while(iterator.hasNext()){
				Entry <String, String>entry = iterator.next();
				response.setHeader(entry.getKey(), entry.getValue());
			}
		}
		
		if(responseObj.getLocale() != null)
			response.setLocale(new Locale(responseObj.getLocale()));

		response.setStatus(responseObj.getResponseStatus());
	    PrintWriter pw = response.getWriter();
	    pw.println(responseObj.getResponseBody());
	}
}