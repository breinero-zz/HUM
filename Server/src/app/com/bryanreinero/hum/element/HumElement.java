package com.bryanreinero.hum.element;

import com.bryanreinero.hum.visitor.Visitable;
import com.google.code.morphia.annotations.Embedded;

public abstract class HumElement implements  Visitable {
	
	public abstract void addParent(HumElement element);
	
	public void addChild(HumElement element) {
        throw new IllegalArgumentException();
    }
	public void addChild(And element) {
        throw new IllegalArgumentException();
    }
	public void addChild(AreaCode element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Carriers element) {
        throw new IllegalArgumentException();
    }
	public void addChild(City element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Compare element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Deterministic element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Continent element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Country element) {
        throw new IllegalArgumentException();
    }
	public void addChild(DMA element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Else element) {
        throw new IllegalArgumentException();
    }
	public void addChild(IP element) {
        throw new IllegalArgumentException();
    }
	public void addChild(If element) {
        throw new IllegalArgumentException();
    }
	public void addChild(L1Domain element) {
        throw new IllegalArgumentException();
    }
	public void addChild(L2Domain element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Language element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Or element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Path element) {
        throw new IllegalArgumentException();
    }
	public void addChild(NonDetermintistic element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RandomNumber element) {
        throw new IllegalArgumentException();
    }
	public void addChild(ReferringURL element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestBody element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestHeader element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestHost element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestURL element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestURLPage element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestURLPath element) {
        throw new IllegalArgumentException();
    }
	public void addChild(RequestURLProtocol element) {
        throw new IllegalArgumentException();
    }
	public void addChild(ResponseBody element) {
        throw new IllegalArgumentException();
    }
	public void addChild(ResponseCode element) {
        throw new IllegalArgumentException();
    }
	public void addChild(ResponseHeader element) {
        throw new IllegalArgumentException();
    }
	public void addChild(SetCookie element) {
        throw new IllegalArgumentException();
    }
	public void addChild(SetVariable element) {
        throw new IllegalArgumentException();
    }
	public void addChild(State element) {
        throw new IllegalArgumentException();
    }
	public void addChild(StringReplace element) {
        throw new IllegalArgumentException();
    }
	public void addChild(UserAgent element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Value element) {
		throw new IllegalArgumentException();
	}
	public void addChild(ZipCode element) {
        throw new IllegalArgumentException();
    }
	public void addChild(Redirect element) {
		throw new IllegalArgumentException();
	}
	public void addChild(Literal element) {
		throw new IllegalArgumentException();
	}
	public void addChild(Name element) {
		throw new IllegalArgumentException();
	}
	public void addChild(Input element) {
		throw new IllegalArgumentException();
	}
	public void addChild(Replacement element)  {
		throw new IllegalArgumentException();
	}
	public void addChild(Substitute element)  {
		throw new IllegalArgumentException();
	}
	public void addChild(Target element)  {
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
}
