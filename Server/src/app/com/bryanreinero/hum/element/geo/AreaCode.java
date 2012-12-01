package com.bryanreinero.hum.element.geo;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.visitor.Visitor;

public class AreaCode extends HumElement implements Visitable {

    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
