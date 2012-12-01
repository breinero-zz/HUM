package com.bryanreinero.hum.element.persistence;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.*;

public class GetData extends PersistenceElement implements Visitable {

	String type = null;
	Query query;
	Fields fields;
	Sort sort;
	Limit limit;
	
	public Query getQuery() {
		return query;
	}

	public Fields getFields() {
		return fields;
	}

	public Limit getLimit() {
		return limit;
	}
	
	public Sort getSort(){
		return sort;
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
	
	@Override
	public void addChild(Limit element){
		limit = element;
	}
	
	@Override
	public void addChild(Sort element){
		sort = element;
	}
}
