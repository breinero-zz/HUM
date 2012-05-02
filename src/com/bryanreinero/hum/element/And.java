/*
 * AndNode.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.element;

import java.util.ArrayList;

import com.bryanreinero.hum.visitor.*;

public class And extends HumElement {

	ArrayList<HumElement> children;
	protected Boolean not;

	/**
     * Gets the value of the not property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNot() {
        return not;
    }
    
    /**
     * Sets the value of the not property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNot(Boolean value) {
        this.not = value;
    }

	@Override
	public void addChild(And element){
		if(children == null)
			children = new ArrayList<HumElement>();
		children.add(element);
	}

	@Override
	public void addChild(Or element){
		if(children == null)
			children = new ArrayList<HumElement>();
		children.add(element);
	}
	
	@Override
	public void addChild(Compare element){
		if(children == null)
			children = new ArrayList<HumElement>();
		children.add(element);
	}


	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public ArrayList<HumElement> getChildren(){
		return children;
	}


	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
