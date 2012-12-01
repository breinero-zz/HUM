package com.bryanreinero.hum.event;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

public class ImpressionTest {
	
	
  @Test
  public void testImpression() {
	  
	  Set<Attribute> attributes = new HashSet<Attribute>();
	  Attribute attribute = new Attribute();
	  attribute.setName("testname");
	  attribute.setValue("testvalue");
	  attributes.add( attribute );
		
	  Creative testCreative = 
			new Creative("test creative", 300, 105, attributes );
	  
	  Impression impression = new Impression();
	  impression.setCampaign(0);
	  impression.setClient(0);
	  impression.setCreative( testCreative );
	  
	  String site = "test site";
	  String page = "test page";
	  String position = "test position";
	  String publisher = "test publisher";
	  Set<Attribute> placementAttributes = new HashSet<Attribute>();
	 
	  Placement placement = new Placement (
			  site, page, position, publisher,
			  placementAttributes
	  );
	  
	  impression.setPlacement( placement );
	  
	  System.out.println( Persistor.convertToDBObject( impression ) );
	  
  }
}
