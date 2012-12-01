package com.bryanreinero.hum.event;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

public class CreativeTest {
  @Test
  public void TestCreative() {
	  Set<Attribute> attributes = new HashSet<Attribute>();
	  Attribute attribute = new Attribute();
	  attribute.setName("testname");
	  attribute.setValue("testvalue");
	  attributes.add(attribute);
		
	  Creative creative = 
			new Creative("test creative", 300, 105, attributes);
	  
	 System.out.println( Persistor.convertToDBObject(creative) );
  }
}
