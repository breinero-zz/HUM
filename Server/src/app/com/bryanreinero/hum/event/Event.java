package com.bryanreinero.hum.event;

import java.util.List;
import org.joda.time.DateTime;

public abstract class Event {
	
	private List<Object> attributes;
	private int client;
	private DateTime date;
	private String type;
	private Long userId;
	
	public List<Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
