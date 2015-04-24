package com.bryanreinero.hum.visitor;

import com.bryanreinero.hum.server.HumException;

public interface Visitable {
	public void accept(Visitor visitor) throws HumException;
}
