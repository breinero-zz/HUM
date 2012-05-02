package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;

public class RequestBody extends HumElement {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
