package com.bryanreinero.hum.element.persistence;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.*;

public class DBCommand extends PersistenceElement implements Visitable {

	private Query query;
	
	public Query getQuery(){
		return query;
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
	public void addChild(Query query){
		this.query = query;
	}

}
