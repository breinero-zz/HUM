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
import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.persistence.*;
import com.bryanreinero.hum.persistence.Deserializer;

public class XMLParser extends DefaultHandler implements Deserializer<String, DecisionTree> {
	
    private static HashMap<String, HumSAXHandler> elements;

    private static XMLReader parser = null;
    private static final String PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
    private Stack<HumElement> stack;
    
    private interface HumSAXHandler 
    { 
        void handleEnd(XMLParser parser) throws Exception;
        void handleStart(XMLParser parser, Attributes atts) throws Exception;
    }
    
	static{
		
		elements = new HashMap<String, HumSAXHandler>();
		
		elements.put("And",
                new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            public void handleStart(XMLParser parser, Attributes atts) {
            	Boolean not = ((atts.getValue("not") != null )? Boolean.parseBoolean(atts.getValue("not")) : false);
                And node = new And();
                node.setNot(not);
                parser.stack.push(node);
            }
        });
		elements.put("Compare", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	Compare compare = new Compare();
            	compare.setOp(Compare.Operator.fromValue(atts.getValue("op")));
                parser.stack.push(compare);
            }
        });
		
		elements.put("ContentType", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new ContentType());
            }
        });

		elements.put("DateTime", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
            	parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new DateTime());
            }
        });
		
		elements.put("DecisionTree", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {}

			public void handleStart(XMLParser parser, Attributes atts) throws Exception {
				String name = atts.getValue("name");
				String timeToLive = atts.getValue("timetolive");
				
				if(name == null || name.length() == 0)
					throw new SAXException("Unallowed attribute \"name\" : "+name);
				if(timeToLive == null || timeToLive.length() == 0)
					throw new SAXException("Unallowed attribute \"timetolive\" : "+timeToLive);
				
				DecisionTree tree = new DecisionTree();
				tree.setName(name);
				tree.setClient(Integer.parseInt(timeToLive));
				parser.stack.push(tree);
			}
		});
		
		elements.put("Format", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts) throws Exception {
				parser.stack.push(new Format());
			}
		});
		
		elements.put("SubTree", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts) throws Exception {
				parser.stack.push(new SubTree());
			}
		});
		
		elements.put("Else", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Else());
            }
        });
        
		elements.put("GetCookie", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new GetCookie());
            }
        });
		
		elements.put("GetVariable", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new GetVariable());
            }
        });
		
		elements.put("IP", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new IP());
            }
        });
		
		elements.put("If", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new If());
            }
        });
		
		elements.put("Input", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Input());
            }
        });

		elements.put("Language", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Language());
            }
        });

		elements.put("Limit", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Limit());
            }
        });
		
		elements.put("Name", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Name());
            }
        });
		
		elements.put("Or",
                new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                Boolean not = ((atts.getValue("not") != null )? Boolean.parseBoolean(atts.getValue("not")) : false);
                Or node = new Or();
                node.setNot(not);
                parser.stack.push(node);
            }
        });
		
		elements.put("Block", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Block());
            }
        });

		elements.put("RandomNumber", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	RandomNumber random = new RandomNumber();
            	if(atts.getValue("max") != null)
            		random.setMax(Integer.parseInt(atts.getValue("max")));
                parser.stack.push(random);
            }
        });

		elements.put("Redirect", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Redirect());
            }
        });
		
		elements.put("ReferringURL", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new ReferringURL());
            }
        });
		
		elements.put("RegularExpression", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RegularExpression());
            }
        });

		elements.put("RequestBody", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestBody());
            }
        });
		
		elements.put("RequestContentType", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestContentType());
            }
        });

		elements.put("RequestHeader", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestHeader());
            }
        });

		elements.put("RequestHost", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestHost());
            }
        });
		
		elements.put("RequestParameter", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestParameter());
            }
        });
		
		elements.put("RequestMethod", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestMethod());
            }
        });
		
		elements.put("RequestURI", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestURI());
            }
        });
		
		elements.put("RequestURLPage", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestURLPage());
            }
        });

		elements.put("RequestURLProtocol", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestURLProtocol());
            }
        });
		
		elements.put("ResponseBody", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new ResponseBody());
            }
        });

		elements.put("ResponseCode", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	ResponseCode obj = new ResponseCode();
                parser.stack.push(obj);
            }
        });

		elements.put("ResponseHeader", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new ResponseHeader());
            }
        });

		elements.put("SetCookie", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                  parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new SetCookie());
            }
        });

		elements.put("SetVariable", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                  parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new SetVariable());
            }
        });
		
		elements.put("Sort", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                  parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Sort());
            }
        });
		
		elements.put("Substitute", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Substitute());
            }
        });
		
		elements.put("Pattern", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
            	parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Pattern());
            }
        });

		elements.put("UserAgent", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new UserAgent());
            }
        });
		
		elements.put("Value", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Value());
            }
        });
		
		elements.put("GetData", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
            	parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	GetData element = new GetData();
            	String type = atts.getValue("type");
            	if(type != null && type.length() != 0)
            		element.setType(type);
                parser.stack.push(element);
            }
        });
		
		elements.put("SetData", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new SetData());
            }
        });
		
		elements.put("Update", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Update());
            }
        });
		
		elements.put("Fields", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Fields());
            }
        });
		
		elements.put("Query", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Query());
            }
        });
		
		elements.put("URLDecode", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new URLDecode());
            }
        });
		
		elements.put("URLEncode", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new URLDecode());
            }
        });
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
	
    public DecisionTree parse(InputStream is) throws IOException, SAXException 
    {
        stack = new Stack<HumElement>();
        parser.parse( new InputSource(is) );
        return (DecisionTree)stack.pop();
    }
    
    public XMLParser() throws SAXException
    {
        parser = XMLReaderFactory.createXMLReader( PARSER_NAME );
        parser.setContentHandler( this );
        parser.setErrorHandler( this );
        
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
	public DecisionTree deserialize(String input) throws IllegalArgumentException {
		try {
			return parse(new ByteArrayInputStream(input.getBytes("UTF-8")));
		} catch (Exception e) {
			throw new IllegalArgumentException( e );	
		}
	}
}
