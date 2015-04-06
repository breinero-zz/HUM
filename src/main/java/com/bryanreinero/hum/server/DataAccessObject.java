package com.bryanreinero.hum.server;

public interface DataAccessObject <K, V> {

	public V get(K key);
	public void persist(V value);
}
