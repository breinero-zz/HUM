package com.bryanreinero.hum.parser;

import org.xml.sax.Attributes;

public interface HumSAXHandler 
{ 
    void handleEnd(XMLParser parser) throws Exception;
    void handleStart(XMLParser parser, Attributes atts) throws Exception;
}