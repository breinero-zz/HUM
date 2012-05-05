package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.Visitor;


public class SetProfile extends NamedVariableElement {

	ArrayList<Pair> pairs = new ArrayList<Pair>();
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}
	
	@Override
	public void addChild(Pair element){
		pairs.add(element);
	}

}
