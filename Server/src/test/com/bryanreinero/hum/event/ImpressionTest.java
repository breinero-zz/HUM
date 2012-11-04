package com.bryanreinero.hum.event;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class ImpressionTest {
	
	
  @Test
  public void testImpression() {
	  
	  List<Object> creativeAttributes = new ArrayList<Object>();
	  creativeAttributes.add("static creative");
		
	  Creative testCreative = 
			new Creative("test creative", 300, 105, creativeAttributes);
	  
	  List<Object> impressionAttributes = new ArrayList<Object>();
	  creativeAttributes.add("test");
	  
	  Impression impression = new Impression();
	  impression.setCampaign(0);
	  impression.setClient(0);
	  impression.setCreative( testCreative );
	  
  }
}
