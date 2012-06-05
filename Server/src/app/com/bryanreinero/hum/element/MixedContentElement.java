package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.GetData;

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
	
	public void addChild(Country element){
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
	public void addChild(RequestHost element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestMethod element){
		children.add(element);
	}
	
	public void addChild(RequestURI element){
		children.add(element);
	}
	
	public void addChild(RequestURLPage element){
		children.add(element);
	}
	
	public void addChild(RequestURLPort element){
		children.add(element);
	}
	
	public void addChild(RequestURLProtocol element){
		children.add(element);
	}
	
	public void addChild(State element){
		children.add(element);
	}	
	
	public void addChild(StringReplace element){
		children.add(element);
	}
	
	public void addChild(ZipCode element){
		children.add(element);
	}
}
