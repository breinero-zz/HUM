package com.bryanreinero.hum.element;

import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class Literal extends HumElement implements Visitable {

	private String value = null;
	
	@Override
	public void accept(Visitor visitor) throws HumException {
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
	
	@Override
	public String toString() {
		return value;
	}
}
