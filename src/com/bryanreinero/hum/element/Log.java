package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;

public class Log extends HumElement {

	private int group = 0;
    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

	public void setGroup(int parseInt) {
		group = parseInt;
	}
}
