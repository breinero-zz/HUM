package com.bryanreinero.hum.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.visitor.*;

public class Executor implements Visitor {

	private HttpServletRequest req;
	
	private Response response = null; 
	
	private Stack<Object> stack = new Stack<Object>();
	
	private Random randGen = new Random();
	private ArrayList<String> executionPath;
	private HashMap<String, String> variables;
	private HashMap<String, String> profileRecords;
	
	public HashMap<String, String> getProfileRecords() {
		return profileRecords;
	}

	private GeoLocation geoLocation;
	
	private URL requestURL;
	
	public Executor(HttpServletRequest req) {
		this.req = req;
		this.geoLocation = GeoLocation.getLocation(req);
		this.executionPath = new ArrayList<String>();
		this.response = new Response();
	}
	
	private URL getRequestURL() {
		if(requestURL == null)
			try {
				requestURL = new URL(req.getRequestURL().toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return requestURL;
	}
	
	private String handleMixedChildren(ArrayList<HumElement> elements) {
		StringBuffer sb = new StringBuffer();
		Iterator<HumElement> iterator = elements.iterator();
		while (iterator.hasNext()) {
			iterator.next().accept(this);
			sb.append(this.stack.pop());
		}
		return sb.toString();
	}
	
	public void setVariable(String name, String value){
		if(variables == null)
			variables = new HashMap<String, String>();
		
		variables.put(name, value);
	}
	
	public String getVariable(String name){
		if(variables == null)
			return null;
		
		return variables.get(name);
	}
	
	public void setProfileRecord(String name, String value){
		if(profileRecords == null)
			profileRecords = new HashMap<String, String>();
		
		variables.put(name, value);
	}
	
	public String getProfileRecord(String name){
		if(profileRecords == null)
			return null;
		
		return profileRecords.get(name);
	}

	@Override
	public void visit(And aBean) {
		boolean result = true;
		Iterator<HumElement> iterator = aBean.getChildren().iterator();
		while (iterator.hasNext()) {
			((Visitable)iterator.next()).accept(this);
			if (!((Boolean) this.stack.pop()).booleanValue()) {
				result = false;
				break;
			}
		}
		stack.push(new Boolean(result && !aBean.isNot()));
	}

	@Override
	public void visit(AreaCode aBean) {
		this.stack.push(this.geoLocation.getAreaCode());
	}

	@Override
	public void visit(Carriers aBean) {
		this.stack.push(this.geoLocation.getCarriers());
	}

	@Override
	public void visit(City aBean) {
		this.stack.push(this.geoLocation.getCity());
	}

	@Override
	public void visit(Compare aBean) {
		aBean.getChildren().get(0).accept(this);
		aBean.getChildren().get(1).accept(this);
		String termB = ((String)this.stack.pop());
		String termA = ((String)this.stack.pop());
		
		switch(aBean.getOp()){
		case ANY:
			this.stack.push(new Boolean(true)); break;
		case CONTAINS:
			this.stack.push( (termA.indexOf(termB) >= 0) ? new Boolean(true) : new Boolean(false));
			break;
		case ENDS_WITH:
			this.stack.push( (termA.indexOf(termB) >= 0) ? new Boolean(true) : new Boolean(false));
			break;
		case EQ: 
			this.stack.push( (Integer.parseInt(termA) == Integer.parseInt(termB)) ? new Boolean(true) : new Boolean(false));
			break;
		case GE: 
			this.stack.push( (Integer.parseInt(termA) >= Integer.parseInt(termB)) ? new Boolean(true) : new Boolean(false));
			break;
		case GT: 
			this.stack.push( (Integer.parseInt(termA) > Integer.parseInt(termB)) ? new Boolean(true) : new Boolean(false));
			break;
		case INSEQ:
			this.stack.push( ( termA.compareToIgnoreCase(termB) != 0) ? new Boolean(true) : new Boolean(false));
			break;
		case LE: 
			this.stack.push( (Integer.parseInt(termA) <= Integer.parseInt(termB)) ? new Boolean(true) : new Boolean(false));
			break;
		case LT: 
			this.stack.push( (Integer.parseInt(termA) < Integer.parseInt(termB)) ? new Boolean(true) : new Boolean(false));
			break;
		case NE: 
			this.stack.push( (Integer.parseInt(termA) != Integer.parseInt(termB)) ? new Boolean(true) : new Boolean(false));
			break;
		case SEQ: 
			this.stack.push( (termA.compareTo(termB) == 0) ? new Boolean(true) : new Boolean(false));
			break;
		case STARTS_WITH: 
			this.stack.push( (termA.startsWith(termB) ) ? new Boolean(true) : new Boolean(false));
			break;
		}
	}
	
	@Override
	public void visit(ContentType aBean) {
		this.getResponse().setContentType(handleMixedChildren(aBean.getChildren()));
	}
	
	@Override
	public void visit(Continent aBean) {
		this.stack.push(this.geoLocation.getContinent());
	}

	@Override
	public void visit(Country aBean) {
		this.stack.push(this.geoLocation.getCountry());
	}

	@Override
	public void visit(DMA aBean) {
		this.stack.push(this.geoLocation.getDMA());
	}

	@Override
	public void visit(Deterministic aBean) {
		
		If ifElement = (If)aBean.getChildren().get(0);
		Else elseElement = null;
		
		if(aBean.getChildren().size() == 2)
			elseElement = (Else)aBean.getChildren().get(1);
			
		ifElement.accept(this);
		
		if( ((Boolean)this.stack.pop()).booleanValue() ) 
			ifElement.getPath().accept(this);
		else if(elseElement != null)
			elseElement.accept(this);
	}

	@Override
	public void visit(Else aBean) {
		aBean.getPath().accept(this);
	}

	@Override
	public void visit(GetCookie aBean) {
		String cookieName = handleMixedChildren(aBean.getChildren());
		
		Cookie[] cookies = this.req.getCookies();
		Cookie targetCookie = null;
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++)
				if(cookies[i].getName().equals(cookieName))
					targetCookie = cookies[i];
		}
		if(targetCookie != null)
			stack.push(targetCookie.getValue());
		else
			stack.push(null);
	}

	@Override
	public void visit(IP aBean) {
		this.stack.push(req.getRemoteAddr());
	}

	@Override
	public void visit(If aBean) {
		aBean.getChild().accept(this);
	}

	@Override
	public void visit(L1Domain aBean) {
		this.stack.push(this.geoLocation.getL1domain());
	}

	@Override
	public void visit(L2Domain aBean) {
		this.stack.push(this.geoLocation.getL2domain());
	}

	@Override
	public void visit(Language aBean) {
		this.stack.push(req.getHeader("Accept-Language"));
	}

	@Override
	public void visit(NonDetermintistic aBean) {
		int randValue = this.randGen.nextInt();
		int cumulative = 0;
		Iterator<Path> iterator = aBean.getPaths().iterator();
		
		while(iterator.hasNext()){
			Path path = iterator.next();
			cumulative += path.getWeight();
			if(randValue <= cumulative){
				path.accept(this);
				break;
			}
		}
	}

	@Override
	public void visit(Or aBean) {
		boolean result = false;
		
		Iterator<HumElement> iterator = aBean.getChildren().iterator();
		while (iterator.hasNext()) {
			iterator.next().accept(this);
			if (((Boolean) this.stack.pop()).booleanValue()) {
				result = true;
				break;
			}
		}
		this.stack.push(new Boolean(result && !aBean.isNot()));
	}
	
	@Override
	public void visit(Path aBean) {
		this.executionPath.add(aBean.getId().toString());
		Iterator<HumElement> iterator = aBean.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(RandomNumber aBean) {
		this.stack.push(Integer.toString(this.randGen.nextInt(aBean.getMax().intValue())));
	}

	@Override
	public void visit(ReferringURL aBean) {
		this.stack.push(req);
	}

	@Override
	public void visit(RequestBody aBean) {
		// TODO Auto-generated method stub
	}

	@Override
	public void visit(RequestHost aBean) {
		this.stack.push(req.getRemoteHost());
	}

	@Override
	public void visit(RequestURL aBean) {
		this.stack.push(req.getRequestURL().toString());
	}

	@Override
	public void visit(RequestURLPage aBean) {
		stack.push(getRequestURL().getFile());
	}

	@Override
	public void visit(RequestURLPath aBean) {
		this.stack.push(getRequestURL().getPath());
	}
	
	@Override
	public void visit(RequestURLPort aBean) {
		this.stack.push(req.getServerPort());
	}

	@Override
	public void visit(RequestURLProtocol aBean) {
		this.stack.push(req.getProtocol());
	}

	@Override
	public void visit(State aBean) {
		this.stack.push(this.geoLocation.getState());
	}

	@Override
	public void visit(StringReplace aBean) {
		aBean.getInput().accept(this);
		Iterator<Replacement> iterator = aBean.getReplacements().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(UserAgent aBean) {
		this.stack.push(req.getHeader("User-Agent"));
	}

	@Override
	public void visit(ZipCode aBean) {
		this.stack.push(this.geoLocation.getZipCode());
	}

	@Override
	public void visit(ResponseBody aBean) {
		this.getResponse().setResponseBody(handleMixedChildren(aBean.getChildren()));
	}

	@Override
	public void visit(ResponseCode aBean) {
		this.getResponse().setResponseStatus(
				Integer.parseInt(handleMixedChildren(aBean.getChildren()))
				);
	}

	@Override
	public void visit(ResponseHeader aBean) {
		aBean.getValue().accept(this);
		aBean.getName().accept(this);
		this.getResponse().setResponseHeader((String)stack.pop(), (String)stack.pop());
	}

	@Override
	public void visit(Redirect element) {
		stack.push(handleMixedChildren(element.getChildren()));
	}

	@Override
	public void visit(SetCookie aBean) {
		//TODO: setting cookies requires special logic
		aBean.getValue().accept(this);
		aBean.getName().accept(this);
		
		Cookie cookie = new Cookie((String)stack.pop(), (String)stack.pop());
		
		this.getResponse().setCookie(cookie.getName(), cookie);
	}

	@Override
	public void visit(SetVariable aBean) {
		aBean.getValue().accept(this);
		aBean.getName().accept(this);
		this.setVariable((String)stack.pop(), (String)stack.pop());
	}

	@Override
	public void visit(Value aBean) {
		stack.push(handleMixedChildren(aBean.getChildren()));
	}

	@Override
	public void visit(DecisionTree aBean) {
		Iterator<HumElement> it = aBean.getChildren().iterator();
		while(it.hasNext())
			it.next().accept(this);
	}

	@Override
	public void visit(Input aBean) {
		stack.push(handleMixedChildren(aBean.getChildren()));
	}

	@Override
	public void visit(Target aBean) {
		stack.push(handleMixedChildren(aBean.getChildren()));
	}

	@Override
	public void visit(RequestHeader aBean) {
		aBean.getValue().accept(this);
		aBean.getName().accept(this);
		this.setVariable((String)stack.pop(), (String)stack.pop());
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

	@Override
	public void visit(Literal element) {
		this.stack.push(element.getValue());
	}

	@Override
	public void visit(Name element) {
		stack.push(handleMixedChildren(element.getChildren()));
	}

	@Override
	public void visit(Replacement element) {
		element.getSubstitute().accept(this);
		element.getTarget().accept(this);
		String regex = (String)stack.pop();
		String replacement = (String)stack.pop();
		String input = (String)stack.pop();
		stack.push(input.replaceAll(regex, replacement));
	}

	@Override
	public void visit(Substitute element) {
		stack.push(handleMixedChildren(element.getChildren()));
	}

	@Override
	public void visit(GetVariable element) {
		stack.push(variables.get(handleMixedChildren(element.getChildren())));
	}

	@Override
	public void visit(SubTree subTree) {
		HUMServer.store.get(subTree.getId()).accept(this);
	}
}
