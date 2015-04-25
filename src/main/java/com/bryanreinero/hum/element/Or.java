/*
 * Or.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.element;

import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.visitor.Visitor;

import java.util.*;

public class Or extends HumElement implements Visitable {
    
    private List<Visitable> children = new ArrayList<Visitable>();
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
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) throws HumException {
        visitor.visit(this);
    }
    
	@Override
	public void addChild(And element){
		children.add(element);
	}

	@Override
	public void addChild(Or element){
		children.add(element);
	}
	
	@Override
	public void addChild(Compare element){
		children.add(element);
	}

    public List<Visitable> getChildren(){
        return children;
    }
}
