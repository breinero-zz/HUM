package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.*;

@Embedded
public class Path extends HumElement {
    private int weight = 0;
    private String id;
    private ArrayList <HumElement> children = new ArrayList<HumElement>();


    public void setChildren(ArrayList <HumElement> children) {
        this.children = children;
    }

    public ArrayList <HumElement> getChildren() {
        return children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
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
	public void addChild(Deterministic element){
		children.add(element);
	}
	
	@Override
	public void addChild(NonDetermintistic element){
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
	public void addChild(ResponseHeader element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetCookie element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetVariable element){
		children.add(element);
	}
	
	@Override
	public void addChild(Log element){
		children.add(element);
	}
	
	
	@Override
	public void addChild(ContentType element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
	
	@Override
	public void addChild(SubTree element){
		if(children == null)
			children = new ArrayList<HumElement>();
		
		children.add(element);
	}
}