package com.bryanreinero.hum.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.element.http.*;

public class XMLParserFactory {

    private static final String PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
    
	public static XMLParser getParser() {
		XMLParser parser;
		try {
			parser = new XMLParser( XMLReaderFactory.createXMLReader( PARSER_NAME ) );
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		parser.addHandler("And", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts) {
				Boolean not = ((atts.getValue("not") != null) ? Boolean
						.parseBoolean(atts.getValue("not")) : false);
				And node = new And();
				node.setNot(not);
				parser.push(node);
			}
		});
		parser.addHandler("Compare", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				Compare compare = new Compare();
				compare.setOp(Compare.Operator.fromValue(atts.getValue("op")));
				parser.push(compare);
			}
		});

		parser.addHandler("ContentType", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new ContentType());
			}
		});

		parser.addHandler("DateTime", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new DateTime());
			}
		});

		parser.addHandler("Specification", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				String name = atts.getValue("name");
				String timeToLive = atts.getValue("timetolive");

				if (name == null || name.length() == 0)
					throw new SAXException("Unallowed attribute \"name\" : "
							+ name);
				if (timeToLive == null || timeToLive.length() == 0)
					throw new SAXException(
							"Unallowed attribute \"timetolive\" : "
									+ timeToLive);

				Specification tree = new Specification();
				tree.setName(name);
				tree.setClient(Integer.parseInt(timeToLive));
				parser.push(tree);
			}
		});

		parser.addHandler("Format", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Format());
			}
		});

		parser.addHandler("SubTree", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new SubTree());
			}
		});

		parser.addHandler("Else", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Else());
			}
		});

		parser.addHandler("GetCookie", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new GetCookie());
			}
		});

		parser.addHandler("GetVariable", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new GetVariable());
			}
		});

		parser.addHandler("IP", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new IP());
			}
		});

		parser.addHandler("If", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new If());
			}
		});

		parser.addHandler("Input", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Input());
			}
		});

		parser.addHandler("Language", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Language());
			}
		});

		parser.addHandler("Name", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Name());
			}
		});

		parser.addHandler("Or", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				Boolean not = ((atts.getValue("not") != null) ? Boolean
						.parseBoolean(atts.getValue("not")) : false);
				Or node = new Or();
				node.setNot(not);
				parser.push(node);
			}
		});

		parser.addHandler("Block", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Block());
			}
		});

		parser.addHandler("RandomNumber", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				RandomNumber random = new RandomNumber();
				if (atts.getValue("max") != null)
					random.setMax(Integer.parseInt(atts.getValue("max")));
				parser.push(random);
			}
		});

		parser.addHandler("Redirect", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Redirect());
			}
		});

		parser.addHandler("ReferringURL", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new ReferringURL());
			}
		});

		parser.addHandler("RegularExpression", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RegularExpression());
			}
		});

		parser.addHandler("RequestBody", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestBody());
			}
		});

		parser.addHandler("RequestContentType", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestContentType());
			}
		});

		parser.addHandler("RequestHeader", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestHeader());
			}
		});

		parser.addHandler("RequestHost", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestHost());
			}
		});

		parser.addHandler("RequestParameter", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestParameter());
			}
		});

		parser.addHandler("RequestMethod", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestMethod());
			}
		});

		parser.addHandler("RequestURI", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestURI());
			}
		});

		parser.addHandler("RequestURLPage", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestURLPage());
			}
		});

		parser.addHandler("RequestURLProtocol", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new RequestURLProtocol());
			}
		});

		parser.addHandler("ResponseBody", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new ResponseBody());
			}
		});

		parser.addHandler("ResponseCode", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				ResponseCode obj = new ResponseCode();
				parser.push(obj);
			}
		});

		parser.addHandler("ResponseHeader", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new ResponseHeader());
			}
		});

		parser.addHandler("SetCookie", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new SetCookie());
			}
		});

		parser.addHandler("SetVariable", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new SetVariable());
			}
		});

		parser.addHandler("Substitute", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Substitute());
			}
		});

		parser.addHandler("Pattern", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Pattern());
			}
		});

		parser.addHandler("UserAgent", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new UserAgent());
			}
		});

		parser.addHandler("Value", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new Value());
			}
		});

		parser.addHandler("URLDecode", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new URLDecode());
			}
		});

		parser.addHandler("URLEncode", new HumSAXHandler() {
			public void handleEnd(XMLParser parser) throws Exception {
				parser.unite();
			}

			public void handleStart(XMLParser parser, Attributes atts)
					throws Exception {
				parser.push(new URLDecode());
			}
		});

		return parser;
	}

}
