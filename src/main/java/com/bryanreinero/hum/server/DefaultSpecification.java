package com.bryanreinero.hum.server;


import com.bryanreinero.hum.element.Specification;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.element.Value;
import com.bryanreinero.hum.element.Literal;
import com.bryanreinero.hum.element.http.ResponseBody;
import com.bryanreinero.hum.element.http.ResponseCode;
import com.bryanreinero.hum.element.http.ResponseHeader;

public class DefaultSpecification {

	// this is tree is returned when the requested match in not found
	public static final Specification spec = new Specification ();
	
	// initialize the defaultTree
	static {
		spec.setTimeToLive(600);

		Literal literal = new Literal();
		Name headerName = new Name();
		literal.setValue("Content-Type");
		headerName.addChild(literal);
		
		literal = new Literal();
		literal.setValue("text");
		Value headerValue = new Value();
		headerValue.addChild(literal);
		
		ResponseCode notFoundHeader = new ResponseCode();
		literal = new Literal();
		literal.setValue("404");
		notFoundHeader.addChild(literal);
		
		ResponseHeader contentType = new ResponseHeader();
		contentType.addChild(headerName);
		contentType.addChild(headerValue);
		
		ResponseBody body = new ResponseBody();
		Literal responseText = new Literal();
		responseText.setValue("No servlet here");
		body.addChild(responseText);
		spec.addChild(body);
		spec.addChild(contentType);
		spec.addChild(notFoundHeader);
	}
}