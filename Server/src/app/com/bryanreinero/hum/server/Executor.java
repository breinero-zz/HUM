package com.bryanreinero.hum.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.Fields;
import com.bryanreinero.hum.element.persistence.GetData;
import com.bryanreinero.hum.element.persistence.Query;
import com.bryanreinero.hum.element.persistence.SetData;
import com.bryanreinero.hum.element.persistence.Update;
import com.bryanreinero.hum.visitor.*;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Executor implements Visitor {

	private Mongo mongo;
	private DB db;
	private DBCollection collection;
	
	private HttpServletRequest req;
	private Response response = null; 
	private Stack<Object> stack = new Stack<Object>();
	
	private Random randGen = new Random();
	private ArrayList<String> executionPath;
	private HashMap<String, String> variables;

	private GeoLocation geoLocation;
	
	private URL requestURL;
	
	public Executor(HttpServletRequest req) {
		this.req = req;
		this.geoLocation = GeoLocation.getLocation(req);
		this.executionPath = new ArrayList<String>();
		this.response = new Response();
		
		try {
		mongo = new Mongo();
		db = mongo.getDB("configurations");
		collection = db.getCollection("ConfigurationTree");
		}catch (Exception e){
			e.printStackTrace();
		}
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

	@Override
	public void visit(And element) {
		boolean result = true;
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while (iterator.hasNext()) {
			((Visitable)iterator.next()).accept(this);
			if (!((Boolean) this.stack.pop()).booleanValue()) {
				result = false;
				break;
			}
		}
		stack.push(new Boolean(result && !element.isNot()));
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
	public void visit(ReferringURL element) {
		this.stack.push(req);
	}

	@Override
	public void visit(RequestBody element) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void visit(RequestContentType element) {
		this.stack.push(req.getContentType());
	}

	@Override
	public void visit(RequestHost element) {
		this.stack.push(req.getRemoteHost());
	}

	@Override
	public void visit(RequestMethod element) {
		this.stack.push(req.getMethod());
	}

	@Override
	public void visit(RequestURLPage aBean) {
		stack.push(getRequestURL().getFile());
	}

	@Override
	public void visit(RequestURI element) {
		this.stack.push(req.getRequestURI());
	}
	
	@Override
	public void visit(RequestContextPath element) {
		this.stack.push(req.getContextPath());
	}
	
	@Override
	public void visit(RequestServletPath element) {
		this.stack.push(req.getServletPath());
	}
	
	@Override
	public void visit(RequestURLPort aBean) {
		this.stack.push(req.getServerPort());
	}

	@Override
	public void visit(RequestURLProtocol element) {
		this.stack.push(req.getProtocol());
	}

	@Override
	public void visit(State element) {
		this.stack.push(this.geoLocation.getState());
	}

	@Override
	public void visit(StringReplace element) {
		element.getInput().accept(this);
		Iterator<RegularExpression> iterator = element.getReplacements().iterator();
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
	public void visit(com.bryanreinero.hum.element.Pattern element) {
		stack.push(handleMixedChildren(element.getChildren()));
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
	public void visit(RegularExpression element) {
		int group = element.getPattern().getGroup();
		element.getPattern().accept(this);
		String regex = (String)stack.pop();
		
		Substitute substitute = element.getSubstitute();
		
		if(substitute != null ){
			String replacement = (String)stack.pop();
			String input = (String)stack.pop();
			stack.push(input.replaceAll(regex, replacement));
		}
		else {
			String input = (String)stack.pop();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			stack.push(((matcher.find())? matcher.group(group) : "" ));
		}
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

	@Override
	public void visit(Fields element) {
		stack.push(handleMixedChildren(element.getChildren()));
	}

	@Override
	public void visit(Query element) {
		stack.push(handleMixedChildren(element.getChildren()));
	}

	@Override
	public void visit(GetData element) {
		DBCursor results;
		element.getQuery().accept(this);
		DBObject query = (DBObject)JSON.parse((String)this.stack.pop() );
		
		if(element.getFields() != null){
			element.getFields().accept(this);
			DBObject fields = (DBObject)JSON.parse((String)this.stack.pop() );
			results = collection.find(query, fields);
		}
		else{
			results = collection.find(query);
		}
		
		StringBuffer sb = new StringBuffer();
		for(DBObject result : results)
			sb.append(result.toString());
		stack.push(sb.toString());
	}

	@Override
	public void visit(Update element) {
		stack.push(variables.get(handleMixedChildren(element.getChildren())));
	}

	@Override
	public void visit(SetData element) {
		element.getQuery().accept(this);
		element.getUpdate().accept(this);
		DBObject update = (DBObject)JSON.parse((String)this.stack.pop() );
		DBObject query = (DBObject)JSON.parse((String)this.stack.pop() );

	}
}
