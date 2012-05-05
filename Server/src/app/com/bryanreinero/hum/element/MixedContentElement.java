package com.bryanreinero.hum.element;

import java.util.ArrayList;
import com.google.code.morphia.annotations.*;

@Embedded
public abstract class MixedContentElement extends HumElement {
	
	ArrayList<HumElement> children = new ArrayList<HumElement>();

	public ArrayList<HumElement> getChildren() {
		return children;
	}
	
	@Override
	public void addChild(Literal element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	@Override
	public void addChild(RequestURL element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	@Override
	public void addChild(GetCookie element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	@Override
	public void addChild(GetVariable element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(City element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(Country element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	@Override
	public void addChild(RandomNumber element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(RequestHost element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(RequestURLPage element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(RequestURLPath element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(RequestURLPort element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(RequestURLProtocol element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(State element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}	
	
	public void addChild(StringReplace element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	public void addChild(ZipCode element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
}
