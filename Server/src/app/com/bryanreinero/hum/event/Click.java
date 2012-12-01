package com.bryanreinero.hum.event;

public class Click extends Event {
	
	private int campaign;
	private Placement placement;
	private Creative creative;
	public static final Type type = Event.Type.click;
	
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
