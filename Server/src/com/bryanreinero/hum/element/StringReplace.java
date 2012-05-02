  	package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.*;

public class StringReplace extends HumElement {

	private Input input = null;

	private ArrayList<Replacement> replacements = null;
    
    public Input getInput() {
		return input;
	}
    
    public ArrayList<Replacement> getReplacements() {
		return replacements;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
	
	@Override
	public void addChild(Input element){
		this.input = element;
	}
	
	@Override
	public void addChild(Replacement element){
		if(replacements == null)
			replacements = new ArrayList<Replacement>(); 
			
		replacements.add(element);
	}

}
