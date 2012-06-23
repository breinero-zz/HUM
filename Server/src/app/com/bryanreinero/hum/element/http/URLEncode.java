package com.bryanreinero.hum.element.http;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.MixedContentElement;
import com.bryanreinero.hum.visitor.Visitor;

public class URLEncode extends MixedContentElement {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
