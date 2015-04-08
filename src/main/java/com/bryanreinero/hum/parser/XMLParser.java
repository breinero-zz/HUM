package com.bryanreinero.hum.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Stack;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.commons.lang3.StringEscapeUtils;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.persistence.Deserializer;

public class XMLParser extends DefaultHandler implements Deserializer<String, Specification> {
	
    private HashMap<String, HumSAXHandler> elements = new HashMap<String, HumSAXHandler>();

    private XMLReader reader = null;
    private Stack<HumElement> stack = new Stack<HumElement>();

	public void addHandler ( String name, HumSAXHandler handler ) {
		elements.put( name, handler );
	}
	
	public void push ( HumElement o ) {
		this.stack.push(o);
	}
	
	public HumElement pop() {
		return this.stack.pop();
	}
	
	public void unite() {
		HumElement element = null;
        try{
        	element = stack.pop();
			element.addParent(stack.peek());
        }catch(IllegalArgumentException e){
        	throw new IllegalArgumentException( stack.peek().getClass().getSimpleName()+" does not accept "+element.getClass().getSimpleName()+" as a child" );
        }
	}
	
    public Specification parse(InputStream is) throws IOException, SAXException 
    {
        reader.parse( new InputSource(is) );
        return (Specification)stack.pop();
    }
    
    public XMLParser( XMLReader reader )
    {
        this.reader = reader;
        this.reader.setContentHandler( this );
        this.reader.setErrorHandler( this );
        stack = new Stack<HumElement>();
    }
    
    @Override
    public void startElement(String namespaceURI, String name, String qName, Attributes atts) throws SAXException 
    {
		if (elements.containsKey(name)) {
			try {
				elements.get(name).handleStart(this, atts);
			} catch (Exception e ) {
				throw new SAXException( e );
			}
		}
    }

    @Override
    public void endElement(String namespaceURI, String name, String qName)
    	throws SAXException
    {
		if (elements.containsKey(name))
			try {
				elements.get(name).handleEnd(this);
			} catch (Exception e) {
				throw new SAXException( e );
			}
		
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
		HumElement element = stack.peek();
		if ((element instanceof MixedContentElement) 
				|| (element instanceof SubTree) 
				){
			Literal literal = new Literal();
			String input = (new String(ch)).substring(start, start+length);
			literal.setValue(StringEscapeUtils.unescapeXml(input));
			stack.peek().addChild(literal);
		}
    }

	@Override
	public Specification deserialize(String input) throws IllegalArgumentException {
		try {
			return parse(new ByteArrayInputStream(input.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new IllegalArgumentException( e );	
		}
	}
}
