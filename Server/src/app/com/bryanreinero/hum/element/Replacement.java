package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class Replacement extends HumElement {
	
	private Target target = null;
	private Substitute substitute = null;

	public Target getTarget() {
		return target;
	}

	public Substitute getSubstitute() {
		return substitute;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) {
		element.addChild(this);
	}
	
	@Override
	public void addChild(Substitute element){
		this.substitute = element;
	}
	
	@Override
	public void addChild(Target element){
		this.target = element;
	}
}
