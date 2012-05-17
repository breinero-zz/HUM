package com.bryanreinero.hum.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Stack;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import org.apache.commons.lang3.StringEscapeUtils;

import com.bryanreinero.hum.element.*;


public class XMLParser extends DefaultHandler {
	
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
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	Boolean not = ((atts.getValue("not") != null )? Boolean.parseBoolean(atts.getValue("not")) : false);
                And node = new And();
                node.setNot(not);
                parser.stack.push(node);
            }
        });
		
		elements.put("AreaCode", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                  parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new AreaCode());
            }
        });

		elements.put("Carriers", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                  parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new Carriers());
            }
        });

		elements.put("City", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                  parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new City());
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
		
		elements.put("Continent", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Continent());
            }
        });

		elements.put("Country", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new Country());
            }
        });

		elements.put("DMA", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
            	parser.stack.push(new DMA());
            }
        });
		
		elements.put("DecisionTree", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {}

			public void handleStart(XMLParser parser, Attributes atts) throws Exception {
				DecisionTree tree = new DecisionTree();
				tree.setName(atts.getValue("name"));
				parser.stack.push(tree);
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
		
		elements.put("Deterministic", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts) throws Exception {
				parser.stack.push(new Deterministic());
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
		
		elements.put("L1Domain", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new L1Domain());
            }
        });

		elements.put("L2Domain", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new L2Domain());
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
		
		elements.put("Name", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Name());
            }
        });
		
		elements.put("NonDetermintistic", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                NonDetermintistic randomNode = (NonDetermintistic)parser.stack.pop();
                randomNode.initialize();
                randomNode.addParent(parser.stack.peek());
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                
                String id = atts.getValue( "id" );
                
                if(id == null || id.length() == 0)
                    throw new SAXException("illegal id");
                
                NonDetermintistic node = new NonDetermintistic();
                node.setId(id);
                parser.stack.push(node);
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
		
		elements.put("Path", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                String id = atts.getValue( "id" );
                String weight = atts.getValue( "weight" );
                
                if(id == null || id.length() == 0)
                    throw new SAXException("Bad path id");
                
                if(weight == null || weight.length() == 0)
                        throw new SAXException("Bad path weight");
                
                Path path = new Path();
                path.setWeight( Integer.parseInt(weight));
                path.setId(id);
                parser.stack.push(path);
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
		
		elements.put("Replacement", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Replacement());
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

		elements.put("RequestURL", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestURL());
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

		elements.put("RequestURLPath", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new RequestURLPath());
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
            	ResponseBody obj = new ResponseBody();
            	if(atts.getValue("group") != null)
            		obj.setGroup(Integer.parseInt(atts.getValue("group")));
                parser.stack.push(obj);
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

		elements.put("State", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new State());
            }
        });
		
		elements.put("StringReplace", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new StringReplace());
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
		
		elements.put("Target", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new Target());
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
		
		elements.put("ZipCode", new HumSAXHandler()
        {
            public void handleEnd(XMLParser parser) throws Exception {
                parser.unite();
            }
            
            public void handleStart(XMLParser parser, Attributes atts) throws Exception {
                parser.stack.push(new ZipCode());
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
    }

    @Override
    public void endElement(String namespaceURI, String name, String qName) throws SAXException
    {
		if (elements.containsKey(name)) {
			try {
				elements.get(name).handleEnd(this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    
    
}
