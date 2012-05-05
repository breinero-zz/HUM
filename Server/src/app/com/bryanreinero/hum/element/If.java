package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.*;

@Embedded
public class If extends HumElement{
    private HumElement child;
    private Path path;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public void addChild(Or element){  
        child = element;
    }
    
    public void addChild(And element){  
        child = element;
    }

    public void addChild(Path element) {
    	 this.path = element;
    }

    public Path getPath() {
        return path;
    }
    
    public HumElement getChild() {
        return child;
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
