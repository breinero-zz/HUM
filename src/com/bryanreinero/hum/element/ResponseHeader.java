package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.*;

public class ResponseHeader extends NamedVariableElement {

    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
