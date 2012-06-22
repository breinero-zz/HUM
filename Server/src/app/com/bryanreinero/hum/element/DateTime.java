package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;

public class DateTime extends MixedContentElement {
	
	private Format format;

	public void addChild(Format format) {
		this.format = format;
	}
	
	public Format getFormat(){
		return format;
	}
	

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
