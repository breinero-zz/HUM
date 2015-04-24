package com.bryanreinero.hum.element;

import java.util.ArrayList;
import java.util.List;

import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.server.HumException;
import com.bryanreinero.hum.visitor.*;

public class Block extends HumElement implements Visitable {
    private List <Visitable> children = new ArrayList<Visitable>();

    public void setChildren(ArrayList <Visitable> children) {
        this.children = children;
    }

    public List <Visitable> getChildren() {
        return children;
    }
    
	@Override
	public void accept(Visitor visitor) throws HumException {
		visitor.visit(this);
	}

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
	
	@Override
	public void addChild(GetVariable element){
		children.add(element);
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
	public void addChild(ResponseCode element){
		children.add(element);
	}
	
	@Override
	public void addChild(ResponseHeader element){
		children.add(element);
	}
	
	@Override
	public void addChild(RequestBody element){
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
	public void addChild(ContentType element){
		children.add(element);
	}
	
	@Override
	public void addChild(SubTree element){
		children.add(element);
	}
}