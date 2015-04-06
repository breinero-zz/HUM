package com.bryanreinero.hum.parser;

import org.xml.sax.Attributes;

public class BasicHandler implements HumSAXHandler {

	@Override
	public void handleEnd(XMLParser parser) throws Exception {
		parser.unite();
	}

	@Override
	public void handleStart(XMLParser parser, Attributes atts) throws Exception {
		// TODO Auto-generated method stub

	}

}
