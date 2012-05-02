package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;

public class RequestURLPort extends HumElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
