package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;

public class Else extends HumElement {
    private Path path;
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public void addChild(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
    
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }
}
