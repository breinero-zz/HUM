package com.bryanreinero.hum.element.persistence;

import com.bryanreinero.hum.element.*;

public abstract class PersistenceElement extends HumElement {
	
	Name name;
	
	@Override
	public void addChild(Name element){
		name = element;
	}
	
	public Name getName(){
		return name;
	}
}
