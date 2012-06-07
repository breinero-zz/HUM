package com.bryanreinero.hum.element;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.bryanreinero.hum.element.http.Redirect;
import com.bryanreinero.hum.element.http.ResponseBody;
import com.bryanreinero.hum.element.http.ResponseHeader;
import com.bryanreinero.hum.element.http.SetCookie;
import com.bryanreinero.hum.visitor.Visitor;
import com.google.code.morphia.annotations.*;

@Entity
public class DecisionTree extends HumElement {

	@Id
	private ObjectId id;
	
	@Embedded
	private String name;
	
	@Embedded
	private int client;
	
	@Embedded
	private int timeToLive;
	
	@Embedded
	private String type;
	
	@Embedded
	private ArrayList<HumElement> children = new ArrayList<HumElement>();
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public ArrayList<HumElement> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<HumElement> children) {
		this.children = children;
	}

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

	@Override
	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addChild(If element){
		children.add(element);
	}
	
	@Override
	public void addChild(Redirect element){
		children.add(element);
	}
	
	@Override
	public void addChild(ResponseBody element){
		children.add(element);
	}
	
	@Override
	public void addChild(ResponseHeader element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetCookie element){
		children.add(element);
	}
	
	@Override
	public void addChild(SetVariable element){
		children.add(element);
	}
	
	@Override
	public void addChild(Block element){
		children.add(element);
	}
	
	@Override
	public void addChild(SubTree element){
		children.add(element);
	}
}
