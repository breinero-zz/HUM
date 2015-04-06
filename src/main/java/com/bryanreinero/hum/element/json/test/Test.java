package com.bryanreinero.hum.element.json.test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.bryanreinero.firehose.Transformer;
import com.bryanreinero.hum.element.Type;
import com.bryanreinero.hum.element.json.Document;
import com.bryanreinero.hum.element.json.Field;
import com.bryanreinero.hum.parser.HumSAXHandler;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.persistence.DAO;
import com.bryanreinero.hum.server.DAOs;
import com.bryanreinero.hum.visitor.Visitable;
import com.bryanreinero.hum.element.json.test.Executor;

public class Test {

	XMLParser parser = null;
	Executor visitor;
	DAOs daoService;
	
	public Test() {
		
		daoService = new DAOs();
		
		
		try {
			daoService.put( "JSONTestInsert", new TestDAO() );
			visitor = new Executor( daoService ); 
			parser = new XMLParser();
			
			parser.addHandler("Document", 
				new HumSAXHandler() {
					private static final String name = "Document";
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
			
			parser.addHandler("DAO", 
					new HumSAXHandler() {

						@Override
						public void handleEnd(XMLParser parser) throws Exception {
							parser.unite();
						}

						@Override
						public void handleStart(XMLParser parser, Attributes atts)
								throws Exception {
							parser.push(new DAO() );
						}
					
					}
				);
			
		} catch ( Exception ex ) {
			ex.printStackTrace();
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
	
	public void get() {
		Visitable element =  (Visitable)parser.pop();
		element.accept( visitor );
	}
	
	public static void main ( String[] args ) {
		
		Test test = new Test();
		try {
			test.startElement( "DAO" );
			
			test.startElement( "Name" );
			test.takeString( "JSONTestInsert" );
			test.endElement( "Name" );
			
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
			test.endElement( "DAO" );
			
			test.get();
		
		} catch ( SAXException ex) {
			ex.printStackTrace();
			return;
		}
	}
}
