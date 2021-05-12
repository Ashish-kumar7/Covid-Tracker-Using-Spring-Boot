package com.example.demo.model;

public class LocationStats {
	private String state;
	private String country;
	private int latesttotalcases;
	private int newCasesToday;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatesttotalcases() {
		return latesttotalcases;
	}
	public void setLatesttotalcases(int latesttotalcases) {
		this.latesttotalcases = latesttotalcases;
	}
	
	public int getNewCasesToday() {
		return newCasesToday;
	}
	public void setNewCasesToday(int newCasesToday) {
		this.newCasesToday = newCasesToday;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latesttotalcases=" + latesttotalcases
				+ ", newCasesToday=" + newCasesToday + "]";
	}

}
