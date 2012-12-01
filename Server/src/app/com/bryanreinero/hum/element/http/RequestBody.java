package com.bryanreinero.hum.element.http;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.*;

public class RequestBody extends HumElement implements Visitable {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
