package com.bryanreinero.hum.server;

import javax.servlet.http.HttpServletRequest;

public class GeoLocation {
	private String continent = "unknown";
	private String country   = "unknown";
	private String state     = "unknown";
	private String city      = "unknown";
	private String areaCode  = "unknown";
	private String zipCode   = "unknown";
	private String DMA       = "unknown";
	private String IP        = "unknown";
	private String l2domain  = "unknown";
	private String l1domain  = "unknown";
	private String carriers  = "unknown";
	
	public static GeoLocation getLocation(HttpServletRequest req){
		return new GeoLocation();
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDMA() {
		return DMA;
	}

	public void setDMA(String dMA) {
		DMA = dMA;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getL2domain() {
		return l2domain;
	}

	public void setL2domain(String l2domain) {
		this.l2domain = l2domain;
	}

	public String getL1domain() {
		return l1domain;
	}

	public void setL1domain(String l1domain) {
		this.l1domain = l1domain;
	}

	public String getCarriers() {
		return carriers;
	}

	public void setCarriers(String carriers) {
		this.carriers = carriers;
	}

}
