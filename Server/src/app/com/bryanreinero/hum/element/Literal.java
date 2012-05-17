package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class Literal extends HumElement {

	private String value = null;
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
