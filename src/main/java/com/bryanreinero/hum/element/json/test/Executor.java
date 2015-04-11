package com.bryanreinero.hum.element.json.test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.Stack;

import javax.servlet.http.Cookie;

import com.bryanreinero.firehose.Transformer;
import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.json.*;
import com.bryanreinero.hum.persistence.DAO;
import com.bryanreinero.hum.server.DAOs;
import com.bryanreinero.hum.visitor.*;
import com.mongodb.BasicDBObject;

public class Executor implements Visitor {

	private final Stack<Object> stack = new Stack<Object>();
	private final DAOs daos;
	
	public Executor( DAOs daos ) throws MalformedURLException {
		this.daos = daos;
	}
	
	private String handleMixedChildren( List<Visitable> list) {
		StringBuffer sb = new StringBuffer();
		
		for ( Visitable element : list ) {
			element.accept(this);
			sb.append(this.stack.pop());
		}
		return sb.toString();
	}
	
	
	@Override
	public void visit(And element) {
		boolean result = true;
		for ( Visitable child : element.getChildren() ) {
			child.accept(this);
			if (!((Boolean) this.stack.pop()).booleanValue()) {
				result = false;
				break;
			}
		}
		stack.push(new Boolean( result && !element.isNot() ) );
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
	}
	
	@Override
	public void visit(Else element) {
		if(element.getIf() != null)
			element.getIf().accept(this);
		else
			element.getPath().accept(this);
	}

	@Override
	public void visit(GetCookie aBean) {
		
	}

	@Override
	public void visit(IP aBean) {
	
	}

	@Override
	public void visit(If element) {
		element.getChild().accept(this);
		if( ((Boolean)this.stack.pop()).booleanValue() ) 
			element.getPath().accept(this);
		else if(element.getElse() != null)
			element.getElse().accept(this);
	}

	@Override
	public void visit(Language aBean) {
	}

	@Override
	public void visit(Or aBean) {
		boolean result = false;
			
	    for ( Visitable element : aBean.getChildren() ) {
	    	element.accept(this);
			if (((Boolean) this.stack.pop()).booleanValue()) {
				result = true;
				break;
			}
		}
		this.stack.push(new Boolean(result && !aBean.isNot()));
	}
	
	@Override
	public void visit(Block aBean) {
		for ( Visitable element : aBean.getChildren() ) 
			element.accept(this);
	}

	@Override
	public void visit(RandomNumber aBean) {
	}

	@Override
	public void visit(ReferringURL element) {
	}

	@Override
	public void visit(RequestBody element) {
	}
	
	@Override
	public void visit(RequestContentType element) {
	}

	@Override
	public void visit(RequestHost element) {
	}

	@Override
	public void visit(RequestMethod element) {
	}

	@Override
	public void visit(RequestURLPage aBean) {
	}

	@Override
	public void visit(RequestURI element) {
	}
	
	@Override
	public void visit(RequestContextPath element) {
	}
	
	@Override
	public void visit(RequestServletPath element) {
	}
	
	@Override
	public void visit(RequestURLPort aBean) {
	}

	@Override
	public void visit(RequestURLProtocol element) {
	}

	@Override
	public void visit(UserAgent aBean) {
	}

	@Override
	public void visit(ResponseBody aBean) {
	}

	@Override
	public void visit(ResponseCode aBean) {
	}

	@Override
	public void visit(ResponseHeader aBean) {
		aBean.getValue().accept(this);
		aBean.getName().accept(this);
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
		
	}

	@Override
	public void visit(SetVariable aBean) {
		aBean.getValue().accept(this);
		aBean.getName().accept(this);
	}

	@Override
	public void visit(Value aBean) {
		stack.push(handleMixedChildren(aBean.getChildren()));
	}

	@Override
	public void visit(Specification aBean) {
		for( Visitable element : aBean.getChildren() )
			element.accept(this);
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
	public void visit(RequestHeader element) {
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
		
		element.getPattern().accept(this);
		String patternS = (String)stack.pop();
		
		element.getInput().accept(this);
		String input = (String)stack.pop();
		
		if(element.getSubstitutes().size() != 0 ){
			for(Substitute substitute : element.getSubstitutes()){
				substitute.accept(this);
			}
		}	
	}

	@Override
	public void visit(Substitute element) {
		stack.push(handleMixedChildren(element.getChildren()));
	}

	@Override
	public void visit(GetVariable element) {
	}

	@Override
	public void visit(SubTree subTree) {
		String name = handleMixedChildren( subTree.getChildren() );
	}

	@Override
	public void visit(DateTime element) {
		SimpleDateFormat format = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeZone(new SimpleTimeZone(0, "GMT"));
        stack.push(format.format(calendar.getTime()));
	}

	@Override
	public void visit(Format format) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void visit(RequestParameter element) {
	}

	@Override
	public void visit(URLDecode element) {
		try {
			stack.push(URLDecoder.decode(handleMixedChildren(element.getChildren()), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println( e.getMessage() );
		}
	}

	@Override
	public void visit(URLEncode element) {
		try {
			stack.push(URLEncoder.encode(handleMixedChildren(element.getChildren()), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println( e.getMessage() );
		}
	}
	
	@Override
	public void visit(Document document) {
		Map<String, Object> doc = new BasicDBObject();
		this.stack.push( doc );
		for( Field field : document.getFields() )
			field.accept(this);
	}

	@Override
	public void visit(Field field) {
		field.getName().accept( this );
		String name = (String)this.stack.pop();
		field.getType().accept( this );
		String type = (String)this.stack.pop();
		field.getValue().accept( this );
		String value = (String)this.stack.pop();
		
		
		@SuppressWarnings("unchecked")
		Map<String, Object> document = (Map<String, Object> )this.stack.peek();
		document.put( name, Transformer.getTransformer( type ).transform( value ) );
		
	}

	@Override
	public void visit(Type type) {
		stack.push(handleMixedChildren(type.getChildren()));
	}

	@Override
	public void visit(DAO dao) {
		dao.getName().accept( this );
		String daoName = (String)this.stack.pop();
		dao.getDocument().accept( this );
		
		Object o = this.daos.execute( daoName, (Map<String, Object>)this.stack.pop() );
		System.out.println( o );
	}
}
