/*
 * Visitor.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.visitor;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.server.HumException;

public interface Visitor {
    
    public void visit(Document document) throws HumException;
    
    public void visit(Block element) throws HumException;

    public void visit(Or element) throws HumException;

    public void visit(And element) throws HumException;
    
    public void visit(Compare element) throws HumException;
    
    public void visit(IP element) throws HumException;

    public void visit(Language  element) throws HumException;
    
    public void visit(ReferringURL element) throws HumException;
    
    public void visit(RequestBody element) throws HumException;

	public void visit(RequestContentType requestContentType) throws HumException;
    
    public void visit(RequestHeader element) throws HumException;

    public void visit(RequestHost element) throws HumException;

	public void visit(RequestMethod requestMethod) throws HumException;
    
    public void visit(RequestURLPage element) throws HumException;
    
    public void visit(RequestURLProtocol element) throws HumException;
    
    public void visit(UserAgent element) throws HumException;
    
    public void visit(ResponseBody element) throws HumException;

	public void visit(Else element) throws HumException;

	public void visit(ResponseCode element) throws HumException;

	public void visit(ResponseHeader element) throws HumException;
	
	public void visit(SetCookie element) throws HumException;

	public void visit(SetVariable setVariable) throws HumException;

	public void visit(GetCookie getCookie) throws HumException;

	public void visit(RandomNumber randomNumber) throws HumException;
	
	public void visit(Redirect element) throws HumException;

	public void visit(Specification element) throws HumException;

	public void visit(Value aBean) throws HumException;

	public void visit(Input aBean) throws HumException;

	public void visit(Pattern aBean) throws HumException;

	public void visit(Literal element) throws HumException;

	public void visit(Name element) throws HumException;

	public void visit(RegularExpression element) throws HumException;

	public void visit(Substitute element) throws HumException;

	public void visit(ContentType element) throws HumException;

	public void visit(GetVariable element) throws HumException;

	public void visit(RequestURLPort element) throws HumException;

	public void visit(SubTree element) throws HumException;

	public void visit(RequestServletPath element) throws HumException;

	public void visit(RequestURI element) throws HumException;

	public void visit(RequestContextPath element) throws HumException;

	public void visit(DateTime dateTime) throws HumException;

	public void visit(Format format) throws HumException;

	public void visit(RequestParameter requestParameter) throws HumException;

	public void visit(URLDecode urlDecode) throws HumException;

	public void visit(URLEncode urlEncode) throws HumException;

	public void visit(Type type) throws HumException;

	public void visit(If element) throws HumException;

	public void visit(DAO dao) throws HumException;
}
