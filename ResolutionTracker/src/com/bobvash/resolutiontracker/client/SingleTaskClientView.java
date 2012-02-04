package com.bobvash.resolutiontracker.client;

import java.io.Serializable;

public class SingleTaskClientView implements Serializable {
	private String description;
	private boolean isCompleted;
	
	public SingleTaskClientView() {
		// TODO Auto-generated constructor stub
	}
	
	public SingleTaskClientView(String description, boolean isCompleted) {
		this.description = description;
		this.isCompleted = isCompleted;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
}
