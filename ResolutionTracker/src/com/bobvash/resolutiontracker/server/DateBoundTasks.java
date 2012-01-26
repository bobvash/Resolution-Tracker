package com.bobvash.resolutiontracker.server;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DateBoundTasks {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String dateString;

	@Persistent
	private Date date;

	@Persistent
	private String[] tasks;

	public DateBoundTasks(Date date, String[] tasks) {
		this.dateString = ResolutionUtils.convertDateToKey(date);
		this.date = date;
		this.tasks = tasks;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getDateString() {
		return dateString;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTasks(String[] tasks) {
		this.tasks = tasks;
	}

	public String[] getTasks() {
		return tasks;
	}

	public void addTask(String newTask) {
		String[] newTasks = new String[this.tasks.length + 1];
		for (int i = 0; i < this.tasks.length; i++) {
			newTasks[i] = this.tasks[i];
		}
		newTasks[newTasks.length-1] = newTask;
		this.tasks = newTasks;
	}

	public void removeTask(String newTask) {
		int removeAtIndex = -1;
		for (int i = 0; i < this.tasks.length; i++) {
			if(this.tasks[i].equals(newTask))
				removeAtIndex = i;
		}
		if (removeAtIndex == -1)
			return;
				
		String[] newTasks = new String[this.tasks.length - 1];
		for (int i = 0; i < this.tasks.length; i++) {
			if (i < removeAtIndex)
				newTasks[i] = this.tasks[i];
			else if (i > removeAtIndex)
				newTasks[i-1] = this.tasks[i];
		}
		this.tasks = newTasks;
	}
}
