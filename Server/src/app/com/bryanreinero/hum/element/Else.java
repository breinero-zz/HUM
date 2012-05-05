package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.*;

@Embedded
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
