package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class Pattern extends MixedContentElement {
	
	private int group = 0;
	
	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public void setGroup(int group){
		this.group = group;
	}
	
	public int getGroup(){
		return group;
	}

}
