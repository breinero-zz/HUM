/*
 * AndNode.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.element;

import java.util.ArrayList;
import java.util.List;

import com.bryanreinero.hum.visitor.*;

public class And extends HumElement implements Visitable {

	List<Visitable> children = new ArrayList<Visitable>();
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

	public void addChild(And element){
		children.add(element);
	}

	public void addChild(Or element){
		children.add(element);
	}
	
	public void addChild(Compare element){
		children.add(element);
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public List<Visitable> getChildren(){
		return children;
	}

	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
	
	@Override
	public String toString () {
		StringBuffer sb = new StringBuffer( ( (not)? "!" : "" )  );
		sb.append("And : [ ");
		
		for ( Visitable child : children )
			sb.append(child);
			
		return sb.append(" ]").toString();
	}
}
