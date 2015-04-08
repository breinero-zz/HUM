package com.bryanreinero.hum.server;

import com.bryanreinero.hum.element.Specification;

public interface ConfigurationDAO {

	public Specification get( String key);
	public void persist( Specification tree );
}
