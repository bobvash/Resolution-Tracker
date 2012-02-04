package com.bobvash.resolutiontracker.client;

import java.io.Serializable;
import java.util.Date;

import com.bobvash.resolutiontracker.server.ResolutionUtils;

public class TaskListClientView implements Serializable {
	private Date date;
	private SingleTaskClientView[] tasks;

	public TaskListClientView() {

	}

	public TaskListClientView(Date date, SingleTaskClientView[] tasks) {
		this.date = date;
		this.tasks = tasks;
	}

	public SingleTaskClientView[] getTasks() {
		return tasks;
	}
	
	public Date getDate() {
		return date;
	}
}