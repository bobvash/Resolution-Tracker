package com.bobvash.resolutiontracker.client;

import java.io.Serializable;
import java.util.Date;

public class TasksClientView implements Serializable {
	private Date date;
	private String[] tasks;

	public TasksClientView() {

	}

	public TasksClientView(Date date, String[] tasks) {
		this.date = date;
		this.tasks = tasks;
	}

	public String[] getTasks() {
		return tasks;
	}
}