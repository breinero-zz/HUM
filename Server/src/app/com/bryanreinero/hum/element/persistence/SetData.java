package com.bryanreinero.hum.element.persistence;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.Value;
import com.bryanreinero.hum.visitor.Visitor;

public class SetData extends PersistenceElement {

	private Value value = null;
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}
	
	@Override
	public void addChild(Value element){
		value = element;
	}
	
	public Value getValue(){
		return value;
	}
}
