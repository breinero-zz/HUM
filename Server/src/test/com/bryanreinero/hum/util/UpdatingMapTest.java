package com.bryanreinero.hum.util;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;

public class UpdatingMapTest {
  
	@Test
	public void f() {
		UpdatingMap<Object, Object> map = new UpdatingMap<Object, Object>();
		Object key = new Object();
		Object value = new Object();
		
		map.put(key, value);
		assertTrue( map.get(key).equals(value));
	}
}
