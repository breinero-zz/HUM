package com.bryanreinero.hum.persistence;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.MixedContentElement;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.element.Type;
import com.bryanreinero.hum.element.json.Document;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.visitor.Visitor;

public class DAO extends MixedContentElement implements Visitable {
	
	private Name name;
	private Type type;
	private Document document;

	@Override
	public void addParent(HumElement parent) {
		parent.addChild(this);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Name getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}

	public Document getDocument() {
		return document;
	}

	@Override
	public void addChild(Document document) {
		this.document = document;
	}

	@Override
	public void addChild( Name name ) {
		this.name = name;
	}
	
	@Override
	public void addChild( Type type ) {
		this.type = type;
	}
}
