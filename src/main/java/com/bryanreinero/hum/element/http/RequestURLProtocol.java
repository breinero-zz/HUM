package com.bryanreinero.hum.element.http;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class RequestURLProtocol extends HumElement implements Visitable {

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit(this);
	}
}
