package com.bryanreinero.hum.element.persistence;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.visitor.Visitor;

public class GetData extends PersistenceElement {

	String type = null;
	Query query;
	Fields fields;
	
	public Query getQuery() {
		return query;
	}

	public Fields getFields() {
		return fields;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public void addChild(Query element){
		query = element;
	}

	@Override
	public void addChild(Fields element){
		fields = element;
	}
}
