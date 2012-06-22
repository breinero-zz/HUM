package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.GetData;
import com.bryanreinero.hum.element.persistence.PutData;
import com.bryanreinero.hum.element.persistence.SetData;

public abstract class MixedContentElement extends HumElement {
	
	ArrayList<HumElement> children = new ArrayList<HumElement>();

	public ArrayList<HumElement> getChildren() {
		return children;
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
	
	public void addChild(State element){
		children.add(element);
	}
	
	public void addChild(ZipCode element){
		children.add(element);
	}
}
