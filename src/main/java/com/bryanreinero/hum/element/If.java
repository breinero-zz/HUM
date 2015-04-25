package com.bryanreinero.hum.element;

import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class If extends HumElement implements Visitable {
    private Visitable child;
    private Block path;
    private Else elseElement;
    
    @Override
    public void accept(Visitor visitor) throws HumException {
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
    
    public Visitable getChild() {
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
