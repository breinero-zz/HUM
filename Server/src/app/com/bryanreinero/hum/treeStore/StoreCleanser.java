package com.bryanreinero.hum.treeStore;

import java.util.ArrayList;
import java.util.Iterator;

import com.bryanreinero.hum.element.And;
import com.bryanreinero.hum.element.AreaCode;
import com.bryanreinero.hum.element.Carriers;
import com.bryanreinero.hum.element.City;
import com.bryanreinero.hum.element.Compare;
import com.bryanreinero.hum.element.ContentType;
import com.bryanreinero.hum.element.Continent;
import com.bryanreinero.hum.element.Country;
import com.bryanreinero.hum.element.DMA;
import com.bryanreinero.hum.element.DecisionTree;
import com.bryanreinero.hum.element.Deterministic;
import com.bryanreinero.hum.element.Else;
import com.bryanreinero.hum.element.GetCookie;
import com.bryanreinero.hum.element.GetProfile;
import com.bryanreinero.hum.element.GetVariable;
import com.bryanreinero.hum.element.HumElement;
import com.bryanreinero.hum.element.IP;
import com.bryanreinero.hum.element.If;
import com.bryanreinero.hum.element.Input;
import com.bryanreinero.hum.element.L1Domain;
import com.bryanreinero.hum.element.L2Domain;
import com.bryanreinero.hum.element.Language;
import com.bryanreinero.hum.element.Literal;
import com.bryanreinero.hum.element.Log;
import com.bryanreinero.hum.element.Name;
import com.bryanreinero.hum.element.NonDetermintistic;
import com.bryanreinero.hum.element.Or;
import com.bryanreinero.hum.element.Pair;
import com.bryanreinero.hum.element.Path;
import com.bryanreinero.hum.element.RandomNumber;
import com.bryanreinero.hum.element.Redirect;
import com.bryanreinero.hum.element.ReferringURL;
import com.bryanreinero.hum.element.Replacement;
import com.bryanreinero.hum.element.RequestBody;
import com.bryanreinero.hum.element.RequestHeader;
import com.bryanreinero.hum.element.RequestHost;
import com.bryanreinero.hum.element.RequestURL;
import com.bryanreinero.hum.element.RequestURLPage;
import com.bryanreinero.hum.element.RequestURLPath;
import com.bryanreinero.hum.element.RequestURLPort;
import com.bryanreinero.hum.element.RequestURLProtocol;
import com.bryanreinero.hum.element.ResponseBody;
import com.bryanreinero.hum.element.ResponseCode;
import com.bryanreinero.hum.element.ResponseHeader;
import com.bryanreinero.hum.element.SetCookie;
import com.bryanreinero.hum.element.SetProfile;
import com.bryanreinero.hum.element.SetVariable;
import com.bryanreinero.hum.element.State;
import com.bryanreinero.hum.element.StringReplace;
import com.bryanreinero.hum.element.SubTree;
import com.bryanreinero.hum.element.Substitute;
import com.bryanreinero.hum.element.Target;
import com.bryanreinero.hum.element.UserAgent;
import com.bryanreinero.hum.element.Value;
import com.bryanreinero.hum.element.ZipCode;
import com.bryanreinero.hum.visitor.Visitor;

public class StoreCleanser implements Visitor, Runnable {
	
	private int cleanseIntervalSleepTimeMS = 21600000;
	private ArrayList<String> configsInScope;

	public void cleanse(){
		TreeStore store = TreeStore.getInstance();
		configsInScope = new ArrayList<String>();
		store.getTree("root").accept(this);
		
		if(configsInScope.size() > 0){
			Iterator<String> iterator = store.getKeySet().iterator();
			
			while(iterator.hasNext()){
				String configID = iterator.next();
				if(!configsInScope.contains(configID))
					store.flushConfig(configID);
			}
		}	
	}

	@Override
	public void visit(Deterministic element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(If element) {
		element.getChild().accept(this);
		element.getPath().accept(this);
	}

	@Override
	public void visit(NonDetermintistic element) {
		Iterator<Path> iterator = element.getPaths().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Path element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Or element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(And element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Compare element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);

	}

	@Override
	public void visit(AreaCode element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Carriers element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(City element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Continent element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Country element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DMA element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IP element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(L1Domain element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(L2Domain element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Language element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ReferringURL element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestBody element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestHeader element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestHost element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestURL element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestURLPage element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestURLPath element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RequestURLProtocol element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(State element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(UserAgent element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ZipCode element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Log element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ResponseBody element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Else element) {
		element.getPath().accept(this);
	}

	@Override
	public void visit(StringReplace element) {
		element.getInput().accept(this);
		Iterator<Replacement> iterator = element.getReplacements().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(ResponseCode element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(ResponseHeader element) {
		element.getName().accept(this);
		element.getValue().accept(this);
	}

	@Override
	public void visit(SetCookie element) {
		element.getName().accept(this);
		element.getValue().accept(this);
	}

	@Override
	public void visit(SetVariable element) {
		element.getName().accept(this);
		element.getValue().accept(this);
	}

	@Override
	public void visit(GetCookie element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(RandomNumber element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Redirect element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(SubTree element) {
		this.configsInScope.add(element.getId());
		DecisionTree newTree = TreeStore.getInstance().getTree(element.getId());
		newTree.accept(this);
	}
	
	@Override
	public void visit(DecisionTree element) {
		Iterator<HumElement> it = element.getChildren().iterator();
		while(it.hasNext())
			it.next().accept(this);
	}


	@Override
	public void visit(Value element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Input element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Target element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Literal element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Name element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(Replacement element) {
		element.getTarget().accept(this);
		element.getSubstitute().accept(this);
	}

	@Override
	public void visit(Substitute element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(ContentType element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(cleanseIntervalSleepTimeMS);
			} catch (InterruptedException e) {}
			cleanse();
		}
	}

	@Override
	public void visit(SetProfile element) {
		element.getValue().accept(this);
		element.getName().accept(this);
	}

	@Override
	public void visit(GetProfile element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(GetVariable element) {
		Iterator<HumElement> iterator = element.getChildren().iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

	@Override
	public void visit(RequestURLPort requestURLPort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Pair pair) {
		// TODO Auto-generated method stub
		
	}

}
