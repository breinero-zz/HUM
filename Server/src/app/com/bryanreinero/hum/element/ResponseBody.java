package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class ResponseBody extends MixedContentElement {

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
