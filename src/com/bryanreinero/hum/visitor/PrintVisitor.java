package com.bryanreinero.hum.visitor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Iterator;

import com.bryanreinero.hum.element.*;
import com.bryanreinero.hum.parser.XMLParser;

public class PrintVisitor implements Visitor {

	private PrintStream out = System.out;
	
	@Override
	public void visit(Deterministic element) {
		String elementName = element.getClass().getSimpleName();
		out.println("<"+element.getClass().getSimpleName()+">");
		Iterator<HumElement>childIterator = element.getChildren().iterator();
		while(childIterator.hasNext())
			childIterator.next().accept(this);
		out.println("</"+elementName+">");
	}

	@Override
	public void visit(If element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NonDetermintistic element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Path element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Or element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(And element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Compare element) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Else element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StringReplace element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ResponseCode element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ResponseHeader element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SetCookie element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(SetVariable setVariable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(GetCookie getCookie) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RandomNumber randomNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Redirect element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(DecisionTree element) {
		String elementName = element.getClass().getSimpleName();
		out.println("<"+element.getClass().getSimpleName()+">");
		Iterator<HumElement>childIterator = element.getChildren().iterator();
		while(childIterator.hasNext())
			childIterator.next().accept(this);
		out.println("</"+elementName+">");
	}

	@Override
	public void visit(Value aBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Input aBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Target aBean) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args){
		try{
		XMLParser parser = new XMLParser();
		String fileLocation = "/Users/breinero/Documents/workspace/HUMServer/resources/test.xml";
		File testConfig = new File(fileLocation);
		FileInputStream fis =  new FileInputStream(fileLocation);
		FileChannel ch = fis.getChannel();
        int size = (int) ch.size();
        MappedByteBuffer buf = ch.map(MapMode.READ_ONLY, 0, size);
        byte[] bytes = new byte[size];
        buf.get(bytes);

		DecisionTree newTree = parser.parse(new ByteArrayInputStream(bytes));
		
		PrintVisitor visitor = new PrintVisitor();
		newTree.accept(visitor);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void visit(Literal element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Name element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Replacement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Substitute element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ContentType contentType) {
		// TODO Auto-generated method stub
		
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
	public void visit(GetVariable getVariable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(RequestURLPort requestURLPort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SubTree subTree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Pair pair) {
		// TODO Auto-generated method stub
		
	}
}
