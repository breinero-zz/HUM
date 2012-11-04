package com.bryanreinero.hum.event;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.mongodb.DBObject;

public class PersistorTest {

	Creative creative;
	Placement placement;
	Impression impression;
	
	static int client = 0;
	static int campaign = 0;

	public PersistorTest() {

		List<Object> creativeAttributes = new ArrayList<Object>();
		creativeAttributes.add("static creative");

		creative = new Creative("test creative", 300, 105, creativeAttributes);

		List<Object> placementAttributes = new ArrayList<Object>();
		placementAttributes.add("ficticious");

		placement = new Placement("test site", "test page",
				"masthead", "BryanMedia", placementAttributes);
		
		
		List<Object> impressionAttributes = new ArrayList<Object>();
		impressionAttributes.add("ficticious impression");
		
		impression = new Impression();
		impression.setAttributes(impressionAttributes);
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
