package com.bryanreinero.hum.server;

import com.bryanreinero.hum.element.DecisionTree;

public interface ConfigurationDAO {

	public DecisionTree get( String key);
	public void persist( DecisionTree tree );
}
