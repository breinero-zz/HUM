package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class StringReplace extends HumElement {

	private Input input = null;

	private ArrayList<RegularExpression> replacements = null;
    
    public Input getInput() {
		return input;
	}
    
    public ArrayList<RegularExpression> getReplacements() {
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
	public void addChild(RegularExpression element){
		if(replacements == null)
			replacements = new ArrayList<RegularExpression>(); 
			
		replacements.add(element);
	}

}
