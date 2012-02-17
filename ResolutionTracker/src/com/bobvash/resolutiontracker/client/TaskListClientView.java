package com.bobvash.resolutiontracker.client;

import java.io.Serializable;
import java.util.Date;


public class TaskListClientView implements Serializable {
	private SingleTaskClientView[] tasks;
	private String dateString;

	public TaskListClientView() {

	}

	public TaskListClientView(String dateString, SingleTaskClientView[] tasks) {
		this.dateString = dateString;
		this.tasks = tasks;
	}

	public SingleTaskClientView[] getTasks() {
		return tasks;
	}
	
	public String getDateString() {
		return dateString;
	}
}