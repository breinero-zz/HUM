package com.bryanreinero.hum.element.json;

import java.util.ArrayList;
import java.util.List;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.visitor.Visitor;

public class Document extends HumElement implements Visitable {
	
	private List<Field> fields = new ArrayList<Field>();
	
	public List<Field> getFields () {
		return fields;
	}
	
	@Override
	public void addChild( Field element ) {
		this.fields.add( element );
	}

	@Override
	public void addParent(HumElement parent) {
		parent.addChild(this);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit( this );
	}

}
