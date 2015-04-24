package com.bryanreinero.hum.element;

import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class DateTime extends MixedContentElement implements Visitable {
	
	private Format format;

	public void addChild(Format format) {
		this.format = format;
	}
	
	public Format getFormat(){
		return format;
	}
	
	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}

}
