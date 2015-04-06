/*
 * Visitor.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.visitor;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.json.*;

public interface Visitor {
    
    public void visit(Document document);
    
    public void visit(Block element);

    public void visit(Or element);

    public void visit(And element);
    
    public void visit(Compare element);
    
    public void visit(IP element);

    public void visit(Language  element);
    
    public void visit(ReferringURL element);
    
    public void visit(RequestBody element);

	public void visit(RequestContentType requestContentType);
    
    public void visit(RequestHeader element);

    public void visit(RequestHost element);

	public void visit(RequestMethod requestMethod);
    
    public void visit(RequestURLPage element);
    
    public void visit(RequestURLProtocol element);
    
    public void visit(UserAgent element);
    
    public void visit(ResponseBody element);

	public void visit(Else element);

	public void visit(ResponseCode element);

	public void visit(ResponseHeader element);
	
	public void visit(SetCookie element);

	public void visit(SetVariable setVariable);

	public void visit(GetCookie getCookie);

	public void visit(RandomNumber randomNumber);
	
	public void visit(Redirect element);

	public void visit(DecisionTree element);

	public void visit(Value aBean);

	public void visit(Input aBean);

	public void visit(Pattern aBean);

	public void visit(Literal element);

	public void visit(Name element);

	public void visit(RegularExpression element);

	public void visit(Substitute element);

	public void visit(ContentType element);

	public void visit(GetVariable element);

	public void visit(RequestURLPort element);

	public void visit(SubTree element);

	public void visit(RequestServletPath element);

	public void visit(RequestURI element);

	public void visit(RequestContextPath element);

	public void visit(DateTime dateTime);

	public void visit(Format format);

	public void visit(RequestParameter requestParameter);

	public void visit(URLDecode urlDecode);

	public void visit(URLEncode urlEncode);

	public void visit(Field field);

	public void visit(Type type);

	void visit(If element);
}
