/*
 * Visitor.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.visitor;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.geo.AreaCode;
import com.bryanreinero.hum.element.geo.Carriers;
import com.bryanreinero.hum.element.geo.City;
import com.bryanreinero.hum.element.geo.Continent;
import com.bryanreinero.hum.element.geo.Country;
import com.bryanreinero.hum.element.geo.DMA;
import com.bryanreinero.hum.element.geo.L1Domain;
import com.bryanreinero.hum.element.geo.L2Domain;
import com.bryanreinero.hum.element.geo.State;
import com.bryanreinero.hum.element.geo.ZipCode;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.*;
import com.bryanreinero.hum.event.*;

public interface Visitor {
    
    public void visit(If element);
    
    public void visit(Block element);

    public void visit(Or element);

    public void visit(And element);
    
    public void visit(Compare element);

    public void visit(AreaCode element);
    
    public void visit(Carriers element);
    
    public void visit(City element);

    public void visit(Continent element);
    
    public void visit(Country element);
    
    public void visit(DMA element);
    
    public void visit(IP element);
    
    public void visit(L1Domain element);

    public void visit(L2Domain element);

    public void visit(Language  element);
    
    public void visit(ReferringURL element);
    
    public void visit(RequestBody element);

	public void visit(RequestContentType requestContentType);
    
    public void visit(RequestHeader element);

    public void visit(RequestHost element);

	public void visit(RequestMethod requestMethod);
    
    public void visit(RequestURLPage element);
    
    public void visit(RequestURLProtocol element);
    
    public void visit(State element);
    
    public void visit(UserAgent element);
    
    public void visit(ZipCode element);
    
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
	
	public void visit(Fields element);
	
	public void visit(Query element);
	
	public void visit(GetData element);
	
	public void visit(Update element);
	
	public void visit(PutData element);

	public void visit(SetData setData);

	public void visit(DateTime dateTime);

	public void visit(Format format);

	public void visit(RequestParameter requestParameter);

	public void visit(Sort sort);

	public void visit(Limit limit);

	public void visit(URLDecode urlDecode);

	public void visit(URLEncode urlEncode);

	public void visit(DBCommand dbCommand);
	
	public void visit(Profile profile);
}
