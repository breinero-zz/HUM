package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class RegularExpression extends HumElement {
	
	private Pattern pattern = null;
	private Substitute substitute = null;

	public Pattern getPattern() {
		return pattern;
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
		substitute = element;
	}
	
	@Override
	public void addChild(Pattern element){
		pattern = element;
	}
}
