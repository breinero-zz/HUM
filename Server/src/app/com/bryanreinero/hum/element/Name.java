package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class Name extends MixedContentElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
