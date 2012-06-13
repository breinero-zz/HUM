package com.bryanreinero.hum.element.persistence;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.Visitor;

public class PutData extends PersistenceElement {
	
	Query query;
	Update update;

	public Query getQuery() {
		return query;
	}

	public Update getUpdate() {
		return update;
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
	public void addChild(Update element){
		update = element;
	}
	
	@Override
	public void addChild(Query element){
		query = element;
	}

}
