package com.bryanreinero.hum.event;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class CreativeTest {
  @Test
  public void TestCreative() {
	  List<Object> creativeAttributes = new ArrayList<Object>();
	  creativeAttributes.add("static creative");
		
	  Creative testCreative = 
			new Creative("test creative", 300, 105, creativeAttributes);
	  
  }
}
