package com.bryanreinero.hum.event;

public class Impression extends Event {

	private int campaign;
	private Placement placement;
	private Creative creative;
	public static final Type type = Event.Type.impression;
	
	public static final String CAMPAIGN_FIELD  = "campaign";
	public static final String PLACEMENT_FIELD = "placement";
	public static final String CREATIVE_FIELD  = "creative";

	public int getCampaign() {
		return campaign;
	}

	public void setCampaign(int campaign) {
		this.campaign = campaign;
	}

	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}

	public Creative getCreative() {
		return creative;
	}

	public void setCreative(Creative creative) {
		this.creative = creative;
	}
}
