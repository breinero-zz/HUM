package com.bryanreinero.hum.element.json;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.bryanreinero.firehose.Transformer;
import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.Type;
import com.bryanreinero.hum.parser.HumSAXHandler;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.test.Executor;
import com.bryanreinero.hum.visitor.Visitable;

public class Test {

	Executor ex = null;
	XMLParser parser = null;
	
	public Test() {

		try {
			ex = new Executor( );
			parser = new XMLParser();
			
			parser.addHandler("Document", 
				new HumSAXHandler() {

					@Override
					public void handleEnd(XMLParser parser) throws Exception {
					}

					@Override
					public void handleStart(XMLParser parser, Attributes atts)
							throws Exception {
						parser.push(new Document() );
					}
				
				}
			);
			
			parser.addHandler("Field", 
					new HumSAXHandler() {

						@Override
						public void handleEnd(XMLParser parser) throws Exception {
							parser.unite();
						}

						@Override
						public void handleStart(XMLParser parser, Attributes atts)
								throws Exception {
							parser.push(new Field() );
						}
					
					}
				);
			
			parser.addHandler("Type", 
					new HumSAXHandler() {

						@Override
						public void handleEnd(XMLParser parser) throws Exception {
							parser.unite();
						}

						@Override
						public void handleStart(XMLParser parser, Attributes atts)
								throws Exception {
							parser.push(new Type() );
						}
					
					}
				);
		} catch (SAXException e) {
			e.printStackTrace();
			System.exit(-1);
		} 
	}
	
	public void startElement( String tag) throws SAXException {
		parser.startElement(null, tag, null, null);
	}
	
	public void endElement( String tag) throws SAXException {
		parser.endElement(null, tag, null );
	}

	private void takeString(String string) throws SAXException {
		parser.characters(string.toCharArray(), 0, string.length() );
	}
	
	public HumElement get() {
		Visitable e =  (Visitable)parser.pop();
		e.accept(ex);
		return null;
	}
	
	public static void main ( String[] args ) {
		
		Test test = new Test();
		try {
			test.startElement( "Document" );
			test.startElement( "Field" );
			
			// field name element
			test.startElement( "Name" );
            test.takeString( "a" );
			test.endElement( "Name" );
			
			// field type element
			test.startElement( "Type" );
			test.takeString( Transformer.TYPE_STRING );
			test.endElement( "Type" );
			
			test.startElement( "Value" );
			test.takeString( "test" );
			test.endElement( "Value" );
			
			test.endElement( "Field" );
			test.endElement( "Document" );
			test.get();
		
		} catch ( SAXException ex) {
			ex.printStackTrace();
			return;
		}
	}
}
