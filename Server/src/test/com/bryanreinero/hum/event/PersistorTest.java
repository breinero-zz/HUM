package com.bryanreinero.hum.event;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.mongodb.DBObject;

public class PersistorTest {

	Creative creative;
	Placement placement;
	Impression impression;
	
	static int client = 0;
	static int campaign = 0;

	public PersistorTest() {

		Attribute attribute = new Attribute();
		attribute.setName("testname");
		attribute.setValue("testvalue");
		Set<Attribute> creativeAttributes = new HashSet<Attribute>();
		creativeAttributes.add(attribute);

		creative = new Creative("test creative", 300, 105, creativeAttributes);

		attribute = new Attribute();
		attribute.setName("type");
		attribute.setValue("test");
		Set<Attribute> placementAttributes = new HashSet<Attribute>();
		placementAttributes.add(attribute);

		placement = new Placement("test site", "test page",
				"masthead", "BryanMedia", placementAttributes);
		
		impression = new Impression();
		impression.setClient(client);
		impression.setCampaign(campaign);
		impression.setCreative(creative);
		impression.setPlacement(placement);
	}

	@Test
	public void TestCreativeToDBObject() {
		DBObject object = Persistor.convertToDBObject(creative);
		System.out.println(object);
	}

	@Test
	public void TestPlacementToDBObject() {
		DBObject object = Persistor.convertToDBObject(placement);
		System.out.println(object);
	}
	
	@Test
	public void TestImpressionToDBObject() {
		DBObject object = Persistor.convertToDBObject(impression);
		System.out.println(object);
	}
}
