package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;

public class Else extends HumElement implements Visitable {
    private Block path;
    private If ifElement;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public void addChild(Block path) {
        this.path = path;
    }

    @Override
    public void addChild(If element) {
        this.ifElement = element;
    }
    
    public Block getPath() {
        return path;
    }
    
    public If getIf(){
    	return ifElement;
    }
    
    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }
}
