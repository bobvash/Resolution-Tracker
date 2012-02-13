package com.bobvash.resolutiontracker.client;

import java.io.Serializable;

public class SingleTaskClientView implements Serializable {
	private String title;
	private String description;
	private String owner;
	private boolean isCompleted;
	
	public SingleTaskClientView() {
		// TODO Auto-generated constructor stub
	}
	
	public SingleTaskClientView(String title, String description, String owner, boolean isCompleted) {
		this.title = title;
		this.description = description;
		this.isCompleted = isCompleted;
		this.owner = owner;
	}
	
	public String getTitle() {
		return title;
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

	public String getOwner() {
		return this.owner;
	}
}
