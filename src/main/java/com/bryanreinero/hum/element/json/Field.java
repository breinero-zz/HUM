package com.bryanreinero.hum.element.json;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.element.Type;
import com.bryanreinero.hum.element.Value;
import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.visitor.Visitor;

public class Field extends HumElement implements Visitable {

	private Value value = null;
	private Type type = null;
	private Name name = null;

	public Value getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}
	
	public Name getName () {
		return name;
	}

	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit( this );
	}

	@Override
	public void addParent(HumElement parent) {
		parent.addChild(this);
	}
	
	@Override
	public void addChild( Name name ) {
		this.name = name;
	}
	
	@Override
	public void addChild( Type type ) {
		this.type = type;
	}
	
	@Override
	public void addChild( Value value ) {
		this.value = value;
	}
	
}
