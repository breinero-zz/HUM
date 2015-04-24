package com.bryanreinero.hum.server;

import java.util.Map;

public interface DAOService {
	public Object execute ( String store, Map<String, Object> request ) throws Exception;
}
