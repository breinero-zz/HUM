package com.bryanreinero.hum.element.http;

import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.MixedContentElement;
import com.bryanreinero.hum.visitor.*;

public class ResponseBody extends MixedContentElement implements Visitable {
	
    @Override
    public void addParent(HumElement element) throws IllegalArgumentException {
        element.addChild(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
