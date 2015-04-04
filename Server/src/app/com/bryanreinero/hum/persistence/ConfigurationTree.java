package com.bryanreinero.hum.persistence;

import com.google.code.morphia.annotations.Id;

public class ConfigurationTree {
	
	@Id
	private String id;
	private String name;
	private String type;
	private int client;
	private int timeToLive;
	private String value;
	
	public ConfigurationTree(){};
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

//	public void addChild(TimeToLive timeToLive ) {
//		setTimeToLive( timeToLive.getValue() );
//	}
//
//	@Override
//	public void addParent(ConfigurationElement element) {
//		//element.addChild( this );
//	}
//
//	@Override
//	public void accept(ConfigurationVisitor visitor) {
//		visitor.visit( this );
//	}
}
