package com.bryanreinero.hum.element;

import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.visitor.Visitor;

public class Type extends MixedContentElement implements Visitable  {

	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement parent) {
		parent.addChild(this);
	}

}
