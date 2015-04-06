package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;

public class ContentType extends MixedContentElement implements Visitable {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
