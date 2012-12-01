package com.bryanreinero.hum.element.http;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.NamedVariableElement;
import com.bryanreinero.hum.visitor.*;

public class ResponseHeader extends NamedVariableElement  implements Visitable {

    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
