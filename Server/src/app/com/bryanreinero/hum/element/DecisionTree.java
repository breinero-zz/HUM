package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.*;

public class DecisionTree extends HumElement {

	@Id
	private String id;
	
	@Embedded
	private ArrayList<HumElement> children = new ArrayList<HumElement>();
	
	public ArrayList<HumElement> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<HumElement> children) {
		this.children = children;
	}

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
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
	public void addChild(Path element){
		children.add(element);
	}
	
	@Override
	public void addChild(SubTree element){
		children.add(element);
	}
}
