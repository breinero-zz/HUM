package com.bryanreinero.hum.element.json;

import java.net.MalformedURLException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.bryanreinero.firehose.Transformer;
import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.element.Type;
import com.bryanreinero.hum.element.Value;
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.parser.XMLParser.HumSAXHandler;
import com.bryanreinero.hum.server.Executor;

public class Test {

	Executor ex = null;
	XMLParser parser = null;
	
	public Test() {
		Executor ex = null;
		XMLParser parser = null;
		try {
			ex = new Executor(null);
			parser = new XMLParser();
			
			parser.addHandler("document", 
				new HumSAXHandler() {

					@Override
					public void handleEnd(XMLParser parser) throws Exception {
						parser.unite();
					}

					@Override
					public void handleStart(XMLParser parser, Attributes atts)
							throws Exception {
						parser.push(new Document() );
					}
				
				}
			);
			
			parser.addHandler("field", 
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
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(-1);
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
		parser.characters(string.toCharArray(), 0, string.length()-1);
	}
	
	public HumElement get() {
		return parser.pop();
	}
	
	public static void main ( String[] args ) {
		
		HumElement e = null;
		
		Test test = new Test();
		try {
			test.startElement( "document" );
			test.startElement( "field" );
			
			// field name element
			test.startElement( "name" );
            test.takeString( "a" );
			test.endElement( "name" );
			
			// field type element
			test.startElement( "type" );
			test.takeString( Transformer.TYPE_STRING );
			test.endElement( "type" );
			
			test.startElement( "value" );
			test.takeString( "test" );
			test.endElement( "value" );
			
			test.endElement( "field" );
			test.endElement( "document" );
			
			e = test.get();
		} catch ( SAXException ex) {
			ex.printStackTrace();
			return;
		}
		System.out.println( e );
		//document.accept( ex );
	}

}
