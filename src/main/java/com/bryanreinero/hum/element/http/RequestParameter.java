package com.bryanreinero.hum.element.http;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.MixedContentElement;
import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class RequestParameter extends MixedContentElement implements Visitable {

	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}
}
