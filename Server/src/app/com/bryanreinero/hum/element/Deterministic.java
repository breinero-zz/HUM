package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.*;

public class Deterministic extends HumElement {

    private String id;
    private ArrayList<HumElement> children = new ArrayList<HumElement>();
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void addChild(If element) {
       children.add(element);
    }
    
    public void addChild(Else element) {
    	children.add(element);
    }

    public ArrayList<HumElement> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<HumElement> children) {
		this.children = children;
	}

	public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
