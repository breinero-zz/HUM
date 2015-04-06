package com.bryanreinero.hum.element;

import com.bryanreinero.hum.element.http.*;
import com.bryanreinero.hum.element.json.Document;
import com.bryanreinero.hum.element.json.Field;

public abstract class HumElement {

	public abstract void addParent( HumElement parent );
	
	public void addChild(HumElement element) { throw new IllegalArgumentException(); }
	public void addChild(And element) { throw new IllegalArgumentException(); }
	public void addChild(Compare element) { throw new IllegalArgumentException(); }
	public void addChild(Else element) { throw new IllegalArgumentException(); }
	public void addChild(IP element) { throw new IllegalArgumentException(); }
	public void addChild(If element) { throw new IllegalArgumentException(); }

	public void addChild(Language element) { throw new IllegalArgumentException(); }
	public void addChild(Or element) { throw new IllegalArgumentException(); }
	public void addChild(Block element) { throw new IllegalArgumentException(); }
	public void addChild(RandomNumber element) { throw new IllegalArgumentException(); }
	public void addChild(ReferringURL element) { throw new IllegalArgumentException(); }
	public void addChild(RequestBody element) { throw new IllegalArgumentException(); }
	public void addChild(RequestContentType element) { throw new IllegalArgumentException(); }
	public void addChild(RequestHeader element) { throw new IllegalArgumentException(); }
	public void addChild(RequestHost element) { throw new IllegalArgumentException(); }
	public void addChild(RequestParameter element) { throw new IllegalArgumentException();}
	public void addChild(RequestMethod element) { throw new IllegalArgumentException(); }
	public void addChild(RequestURLPage element) { throw new IllegalArgumentException(); }
	public void addChild(RequestURI element) { throw new IllegalArgumentException(); }
	public void addChild(RequestURLProtocol element) { throw new IllegalArgumentException(); }
	public void addChild(ResponseBody element) { throw new IllegalArgumentException(); }
	public void addChild(ResponseCode element) { throw new IllegalArgumentException(); }
	public void addChild(ResponseHeader element) { throw new IllegalArgumentException(); }
	public void addChild(SetCookie element) { throw new IllegalArgumentException(); }
	public void addChild(SetVariable element) { throw new IllegalArgumentException(); }

	public void addChild(UserAgent element) { throw new IllegalArgumentException(); }
	public void addChild(Value element) { throw new IllegalArgumentException(); }

	public void addChild(Redirect element) { throw new IllegalArgumentException(); }
	public void addChild(Literal element) { throw new IllegalArgumentException(); }
	public void addChild(Name element) { throw new IllegalArgumentException(); }
	public void addChild(Input element) {
		throw new IllegalArgumentException();
	}
	public void addChild(RegularExpression element)  {
		throw new IllegalArgumentException();
	}
	public void addChild(Substitute element)  {
		throw new IllegalArgumentException();
	}
	public void addChild(Pattern element)  {
		throw new IllegalArgumentException();
	}

	public void addChild(ContentType element)  {
		throw new IllegalArgumentException();
	}

	public void addChild(GetVariable element) {
		throw new IllegalArgumentException();
	}

	public void addChild(SubTree element) {
		throw new IllegalArgumentException();
	}

	public void addChild(GetCookie element) {
		throw new IllegalArgumentException();
	}
	
	public void addChild(RequestURLPort element) { throw new IllegalArgumentException(); }

	public void addChild(DateTime element) { throw new IllegalArgumentException(); }
	
	public void addChild(URLDecode element) { throw new IllegalArgumentException(); }
	public void addChild(URLEncode element) { throw new IllegalArgumentException(); }

	public void addChild(Field element) { throw new IllegalArgumentException(); }

	public void addChild(Type type) { throw new IllegalArgumentException(); }

	public void addChild(Document document) {
		throw new IllegalArgumentException();
	}
	
}
