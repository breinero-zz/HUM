package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;
import com.google.code.morphia.annotations.Embedded;

@Embedded
public class RequestURLPath extends HumElement {

    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
