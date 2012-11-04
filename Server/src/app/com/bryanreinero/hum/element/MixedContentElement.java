package com.bryanreinero.hum.element;

import java.util.ArrayList;
import java.util.List;

import com.bryanreinero.hum.element.geo.City;
import com.bryanreinero.hum.element.geo.Country;
import com.bryanreinero.hum.element.geo.State;
import com.bryanreinero.hum.element.geo.ZipCode;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.*;

public abstract class MixedContentElement extends HumElement {
	
	ArrayList<HumElement> children = new ArrayList<HumElement>();

	public List<HumElement> getChildren() {
		return children;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if(children.size() > 0 )
		{
			sb.append( children );
		}
		
		return sb.toString();
	}
	
	@Override
	public void addChild(Literal element){
		children.add(element);
	}
	
	@Override
	public void addChild(DateTime element){
		children.add(element);
	}
	
	@Override
	public void addChild(DBCommand element){
		children.add(element);
	}
	
	@Override
	public void addChild(GetCookie element){
		children.add(element);
	}
	
	@Override
	public void addChild(GetData element){
		children.add(element);
	}
	
	@Override
	public void addChild(GetVariable element){
		children.add(element);
	}
	
	public void addChild(City element){
		children.add(element);
	}
	
	@Override
	public void addChild(Country element){
		children.add(element);
	}
	
	@Override
	public void addChild(PutData element){
		children.add(element);
	}
	
	@Override
	public void addChild(RandomNumber element){
		children.add(element);
	}
	
	@Override
	public void addChild(RegularExpression element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestBody element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestContentType element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestHeader element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestHost element){
		children.add(element);
	}
	
	public void addChild(RequestParameter	 element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestMethod element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestURI element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestURLPage element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestURLPort element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestURLProtocol element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetData element){
		children.add(element);
	}
	
	@Override
	public void addChild(State element){
		children.add(element);
	}

	@Override
	public void addChild(URLDecode element){
		children.add(element);
	}
	
	@Override
	public void addChild(URLEncode element){
		children.add(element);
	}
	
	@Override
	public void addChild(ZipCode element){
		children.add(element);
	}
	
	
}
