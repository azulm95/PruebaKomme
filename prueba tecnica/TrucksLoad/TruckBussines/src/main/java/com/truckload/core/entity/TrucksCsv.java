package com.truckload.core.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrucksCsv {
	private Map<String,String> errors = new HashMap<>();
	private int susses = 0;
	private int error = 0;
	Trucks t;
	public TrucksCsv(Trucks t) {
		super();
		this.t = t;
	}
	private String path;
	private List<String[]> csvContents;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<String[]> getCsvContents() {
		return csvContents;
	}
	public void setCsvContents(List<String[]> csvContents) {
		this.csvContents = csvContents;
	}
	public Trucks getT() {
		return t;
	}
	public void setT(Trucks t) {
		this.t = t;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	
	public int getSusses() {
		return susses;
	}
	public void setSusses(int susses) {
		this.susses = susses;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	
	
	
	
	
	
}
