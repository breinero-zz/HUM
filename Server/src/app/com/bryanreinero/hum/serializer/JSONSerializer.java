package com.bryanreinero.hum.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.xml.sax.SAXException;

import com.mongodb.util.*;

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
import com.bryanreinero.hum.element.MixedContentElement;
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
import com.bryanreinero.hum.parser.XMLParser;
import com.bryanreinero.hum.visitor.Visitor;

public class JSONSerializer implements Visitor {
	
	private StringBuilder buf = new StringBuilder();
	
	public void serialize(HumElement element, StringBuilder buf){
		this.buf = buf;
		element.accept(this);
	}
	
	private void serializeMixedContent(ArrayList<HumElement> arrayList){
		boolean isFirstElement = true;
		
		
		buf.append("[ ");
		for ( HumElement child : arrayList ){
			if(!isFirstElement)
				buf.append(", ");
			else
				isFirstElement = false;
			
			if( ! (child instanceof Literal))
				buf.append( "\""+child.getClass().getSimpleName()+"\" : ");
			child.accept(this);
		}
		buf.append(" ]");
	}
	
	public void visit(MixedContentElement element) {
		serializeMixedContent(element.getChildren());
	}

	@Override
	public void visit(Deterministic element) {
		buf.append("{ \"Deterministic\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(If element) {
		buf.append("{ \"If\" : ");
		element.getPath().accept(this);
		buf.append(" }");
	}

	@Override
	public void visit(NonDetermintistic element) {
		buf.append("{ \"NonDeterministic\" : ");
		buf.append("[ ");
		boolean isFirstElement = true;
		for ( HumElement child : element.getPaths() ){
			if(!isFirstElement)
				buf.append(", ");
			else
				isFirstElement = false;
			child.accept(this);
		}
		buf.append(" ] }");
	}

	@Override
	public void visit(Path element) {
		buf.append("{ \"Path\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Or element) {
		buf.append("{ \"Or\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(And element) {
		buf.append("{ \"And\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Compare element) {
		buf.append("{ \"Compare\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(AreaCode element) {
		buf.append("{ \"AreaCode\" : 1 }");
	}

	@Override
	public void visit(Carriers element) {
		buf.append("{ \"Carriers\" : 1 }");
	}

	@Override
	public void visit(City element) {
		buf.append("{ \"City\" : 1 }");
	}

	@Override
	public void visit(Continent element) {
		buf.append("{ \"Continent\" : 1 }");
	}

	@Override
	public void visit(Country element) {
		buf.append("{ \"Country\" : 1 }");
	}

	@Override
	public void visit(DMA element) {
		buf.append("{ \"DMA\" : 1 }");
	}

	@Override
	public void visit(IP element) {
		buf.append("{ \"IP\" : 1 }");
	}

	@Override
	public void visit(L1Domain element) {
		buf.append("{ \"L1Domain\" : 1 }");
	}

	@Override
	public void visit(L2Domain element) {
		buf.append("{ \"L2Domain\" : 1 }");
	}

	@Override
	public void visit(Language element) {
		buf.append("{ \"Language\" : 1 }");
	}

	@Override
	public void visit(ReferringURL element) {
		buf.append("{ \"ReferringURL\" : 1 }");
	}

	@Override
	public void visit(RequestBody element) {
		buf.append("{ \"RequestBody\" : 1 }");
	}

	@Override
	public void visit(RequestHeader element) {
		buf.append("{ \"RequestHeader\" : 1 }");
	}

	@Override
	public void visit(RequestHost element) {
		buf.append("{ \"RequestHost\" : 1 }");
	}

	@Override
	public void visit(RequestURL element) {
		buf.append("{ \"RequestURL\" : 1 }");
	}

	@Override
	public void visit(RequestURLPage element) {
		buf.append("{ \"RequestURLPage\" : 1 }");
	}

	@Override
	public void visit(RequestURLPath element) {
		buf.append("{ \"RequestURLPath\" : 1 }");
	}

	@Override
	public void visit(RequestURLProtocol element) {
		buf.append("{ \"RequestURLProtocol\" : 1 }");
	}

	@Override
	public void visit(State element) {
		buf.append("{ \"State\" : 1 }");
	}

	@Override
	public void visit(UserAgent element) {
		buf.append("{ \"UserAgent\" : 1 }");
	}

	@Override
	public void visit(ZipCode element) {
		buf.append("{ \"ZipCode\" : 1 }");
	}

	@Override
	public void visit(Log element) {
		buf.append("{ \"Log\" : 1 }");
	}

	@Override
	public void visit(ResponseBody element) {
		buf.append("{ \"ResponseBody\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Else element) {
		buf.append("{ \"Else\" : ");
		element.getPath().accept(this);
		buf.append(" }");
	}

	@Override
	public void visit(StringReplace element) {
		buf.append("{ \"StringReplace\" : {");
		element.getInput().accept(this);
		buf.append(" , ");
		
		boolean isFirstElement = true;
		
		buf.append("[ ");
		for ( HumElement child : element.getReplacements() ){
			if(!isFirstElement)
				buf.append(", ");
			else
				isFirstElement = false;
			child.accept(this);
		}
		buf.append(" ]");
		buf.append(" }");
		buf.append(" }");

	}

	@Override
	public void visit(ResponseCode element) {
		buf.append("{ \"ResponseCode\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(ResponseHeader element) {
		buf.append("{ \"ResponseHeader\" : ");
		element.getName().accept(this);
		buf.append(" , ");
		element.getValue().accept(this);
		buf.append(" }");
	}

	@Override
	public void visit(SetCookie element) {
		buf.append("{ \"SetCookie\" : ");
		element.getName().accept(this);
		buf.append(" , ");
		element.getValue().accept(this);
		buf.append(" }");
	}

	@Override
	public void visit(SetVariable element) {
		buf.append("{ \"Name\" : ");
		element.getName().accept(this);
		buf.append(" , \"Value\" : ");
		element.getValue().accept(this);
		buf.append(" }");
	}

	@Override
	public void visit(GetCookie element) {
		buf.append("{ \"GetCookie\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(RandomNumber element) {
		buf.append("{ \"RandomNumber\" : ");
		if(element.getMax() != null)
			buf.append(" { \"max\" : "+element.getMax()+ " }");
		buf.append(" }");
	}

	@Override
	public void visit(Redirect element) {
		buf.append("{ \"Redirect\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(DecisionTree element) {
		buf.append("{ ");
		buf.append("\"_id\" : "+element.getId()+" , ");
		
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Value element) {
		buf.append("{ \"Value\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Input element) {
		buf.append("{ \"Input\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Target element) {
		buf.append("{ \"Target\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(Literal element) {
		buf.append(" \""+JSONObject.escape(element.getValue())+"\"");
	}

	@Override
	public void visit(Name element) {
		serializeMixedContent(element.getChildren());
		
//		buf.append( "\""+element.getChildren().get(0).getClass().getSimpleName()+"\" : ");
//		element.getChildren().get(0).accept(this);
//		buf.append(" , ");
//		buf.append( "\""+element.getChildren().get(1).getClass().getSimpleName()+"\" : ");

	}

	@Override
	public void visit(Replacement element) {
		buf.append("{ \"Replacement\" : ");
		element.getTarget().accept(this);
		buf.append(" , ");
		element.getSubstitute().accept(this);
		buf.append(" }");
	}

	@Override
	public void visit(Substitute element) {
		buf.append("{ \"Substitute\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(ContentType element) {
		buf.append("{ \"ContentType\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(SetProfile setProfile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(GetProfile getProfile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(GetVariable element) {
		buf.append("{ \"ContentType\" : ");
		serializeMixedContent(element.getChildren());
		buf.append(" }");
	}

	@Override
	public void visit(RequestURLPort element) {
		buf.append("{ \"RequestURLPort\" : 1 }");
	}

	@Override
	public void visit(SubTree element) {
		buf.append("{ \"SubTree\" : { \"id\" : \""+element.getId()+"\" } }");
	}

	@Override
	public void visit(Pair pair) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args){
		try {
			XMLParser parser = new XMLParser();
			StringBuilder buf = new StringBuilder();
			JSONSerializer serializer = new JSONSerializer();
			File configFile = new File("/Users/breinero/Sites/root.xml");
			DecisionTree tree = parser.parse(new FileInputStream( configFile ));
			
			serializer.serialize(tree, buf);
			System.out.println(buf.toString());
			
			Object obj = JSON.parse(buf.toString());
			
			System.out.println(obj);
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		;
	}

}
