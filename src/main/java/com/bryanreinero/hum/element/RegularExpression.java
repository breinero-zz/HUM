package com.bryanreinero.hum.element;

import java.util.ArrayList;
import java.util.List;

import com.bryanreinero.hum.visitor.*;

public class RegularExpression extends HumElement implements Visitable {
	
	private Pattern pattern = null;
	private Input input = null;
	private List<Substitute> substitutes = new ArrayList<Substitute>();

	public Pattern getPattern() {
		return pattern;
	}

	public List<Substitute> getSubstitutes() {
		return substitutes;
	}
	
    public Input getInput() {
		return input;
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
		substitutes.add(element);
	}
	
	@Override
	public void addChild(Pattern element){
		pattern = element;
	}
	
	@Override
	public void addChild(Input element){
		this.input = element;
	}
}
