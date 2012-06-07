package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.SetData;
import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class Block extends HumElement {
    private ArrayList <HumElement> children = new ArrayList<HumElement>();

    public void setChildren(ArrayList <HumElement> children) {
        this.children = children;
    }

    public ArrayList <HumElement> getChildren() {
        return children;
    }
    
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
	
	@Override
	public void addChild(GetVariable element){
		children.add(element);
	}
	
	@Override
	public void addChild(If element){
		children.add(element);
	}
	
	@Override
	public void addChild(Redirect element){
		children.add(element);
	}
	
	@Override
	public void addChild(ResponseBody element){
		children.add(element);
	}
	
	@Override
	public void addChild(ResponseCode element){
		children.add(element);
	}
	
	@Override
	public void addChild(ResponseHeader element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestBody element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetCookie element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetData element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetVariable element){
		children.add(element);
	}
	
	@Override
	public void addChild(ContentType element){
		children.add(element);
	}
	
	@Override
	public void addChild(SubTree element){
		children.add(element);
	}
}