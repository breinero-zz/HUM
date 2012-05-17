/*
 * Visitor.java
 * Written by: Bryan Reinero (breinero@gmail.com)
 * Created on: 09/01/11 03:14 PM
 */

package com.bryanreinero.hum.visitor;

import com.bryanreinero.hum.element.*;

public interface Visitor {
    
    public void visit(Deterministic element);
    
    public void visit(If element);
    
    public void visit(NonDetermintistic element);
    
    public void visit(Path element);

    public void visit(Or element);

    public void visit(And element);
    
    public void visit(Compare element);

    public void visit(AreaCode element);
    
    public void visit(Carriers element);
    
    public void visit(City element);

    public void visit(Continent element);
    
    public void visit(Country element);
    
    public void visit(DMA element);
    
    public void visit(IP element);
    
    public void visit(L1Domain element);

    public void visit(L2Domain element);

    public void visit(Language  element);
    
    public void visit(ReferringURL element);
    
    public void visit(RequestBody element);
    
    public void visit(RequestHeader element);

    public void visit(RequestHost element);
    
    public void visit(RequestURL element);
    
    public void visit(RequestURLPage element);
    
    public void visit(RequestURLPath element);
    
    public void visit(RequestURLProtocol element);
    
    public void visit(State element);
    
    public void visit(UserAgent element);
    
    public void visit(ZipCode element);
    
    public void visit(ResponseBody element);

	public void visit(Else element);

	public void visit(StringReplace element);

	public void visit(ResponseCode element);

	public void visit(ResponseHeader element);
	
	public void visit(SetCookie element);

	public void visit(SetVariable setVariable);

	public void visit(GetCookie getCookie);

	public void visit(RandomNumber randomNumber);
	
	public void visit(Redirect element);

	public void visit(DecisionTree element);

	public void visit(Value aBean);

	public void visit(Input aBean);

	public void visit(Target aBean);

	public void visit(Literal element);

	public void visit(Name element);

	public void visit(Replacement element);

	public void visit(Substitute element);

	public void visit(ContentType contentType);

	public void visit(GetVariable getVariable);

	public void visit(RequestURLPort requestURLPort);

	public void visit(SubTree subTree);
}
