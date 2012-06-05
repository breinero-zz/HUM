package com.bryanreinero.hum.persistence;

public interface Deserializer <I, E> {
	public E deserialize(I input);
}
