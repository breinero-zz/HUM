package com.bryanreinero.hum.element;

import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class Pattern extends MixedContentElement implements Visitable {
	
	private int group = 0;
	
	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit(this);
	}
	
	public void setGroup(int group){
		this.group = group;
	}
	
	public int getGroup(){
		return group;
	}

}
