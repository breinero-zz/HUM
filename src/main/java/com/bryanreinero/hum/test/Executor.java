package com.bryanreinero.hum.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.Stack;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bryanreinero.firehose.Transformer;
import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.json.*;
import com.bryanreinero.hum.server.HUMServer;
import com.bryanreinero.hum.server.Response;
import com.bryanreinero.hum.visitor.*;
import com.mongodb.BasicDBObject;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Executor implements Visitor {

	private final HttpServletRequest req = null;
	private String requestBody = null;
	private final URL requestURL = null;
	private final Stack<Object> stack = new Stack<Object>();
	private final Map<String, String> variables = new HashMap<String, String>();
	private final Random randGen = new Random();

	private Response response = null; 
	
	
	private static final Logger logger = LogManager.getLogger( Executor.class.getName() );
	
	public Executor() {
		this.response = new Response();
	}
	
	private String getBody() {
		if (requestBody == null) {
			StringBuilder stringBuilder = new StringBuilder();
			BufferedReader bufferedReader = null;
			try {
				InputStream inputStream = req.getInputStream();
				if (inputStream != null) {
					bufferedReader = new BufferedReader(new InputStreamReader(
							inputStream));
					char[] charBuffer = new char[128];
					int bytesRead = -1;
					while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
						stringBuilder.append(charBuffer, 0, bytesRead);
					}
				} else {
					stringBuilder.append("");
				}
			} catch(IOException ioe) {
				logger.warn( ioe.getMessage() );
			}
			finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException ex) {
						logger.warn( ex.getMessage() );
					}
				}
			}
			requestBody = stringBuilder.toString();
		}
		return this.requestBody;
	}
	
	private URL getRequestURL() {
		return requestURL;
	}
	
	private String handleMixedChildren( List<Visitable> list) {
		StringBuffer sb = new StringBuffer();
		
		for ( Visitable element : list ) {
			element.accept(this);
			sb.append(this.stack.pop());
		}
		return sb.toString();
	}
	
	public Response getResponse() {
		return response;
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
		this.getResponse().setContentType(handleMixedChildren(aBean.getChildren()));
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
	public void visit(If element) {
		element.getChild().accept(this);
		if( ((Boolean)this.stack.pop()).booleanValue() ) 
			element.getPath().accept(this);
		else if(element.getElse() != null)
			element.getElse().accept(this);
	}

	@Override
	public void visit(Language aBean) {
		this.stack.push(req.getHeader("Accept-Language"));
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
		this.stack.push(Integer.toString(this.randGen.nextInt(aBean.getMax().intValue())));
	}

	@Override
	public void visit(ReferringURL element) {
		this.stack.push(req);
	}

	@Override
	public void visit(RequestBody element) {
			stack.push(getBody());
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
	public void visit(UserAgent aBean) {
		this.stack.push(req.getHeader("User-Agent"));
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
		variables.put((String)stack.pop(), (String)stack.pop());
	}

	@Override
	public void visit(Value aBean) {
		stack.push(handleMixedChildren(aBean.getChildren()));
	}

	@Override
	public void visit(DecisionTree aBean) {
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
		stack.push(req.getHeader(handleMixedChildren(element.getChildren())));
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
		Pattern pattern = Pattern.compile(patternS);
		
		element.getInput().accept(this);
		String input = (String)stack.pop();
		Matcher matcher = pattern.matcher(input);
		
		if(element.getSubstitutes().size() != 0 ){
			for(Substitute substitute : element.getSubstitutes()){
				substitute.accept(this);
				stack.push(matcher.replaceAll((String)stack.pop()));
			}
		}
		else {
			if(matcher.find())
				stack.push( matcher.group(1) );
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
		String name = handleMixedChildren( subTree.getChildren() );
		HUMServer.store.get( name ).accept(this);
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
		stack.push(req.getParameter(handleMixedChildren(element.getChildren())));
	}

	@Override
	public void visit(URLDecode element) {
		try {
			stack.push(URLDecoder.decode(handleMixedChildren(element.getChildren()), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.warn( e.getMessage() );
		}
	}

	@Override
	public void visit(URLEncode element) {
		try {
			stack.push(URLEncoder.encode(handleMixedChildren(element.getChildren()), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.warn( e.getMessage() );
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
}
