package com.bryanreinero.hum.element;

import com.bryanreinero.hum.element.geo.Block;
import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class If extends HumElement{
    private HumElement child;
    private Block path;
    private Else elseElement;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public void addChild(And element){  
        child = element;
    }
    
    @Override
    public void addChild(Else element){  
    	elseElement = element;
    }
    
    @Override
    public void addChild(Or element){  
        child = element;
    }

    @Override
    public void addChild(Block element) {
    	 path = element;
    }

    public Block getPath() {
        return path;
    }
    
    public HumElement getChild() {
        return child;
    }
    
    public Else getElse(){
    	return elseElement;
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
