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
	private String[] taskDescription;

	@Persistent
	private Boolean[] isComplete;

	public DateBoundTasks(Date date, String[] taskDescription,
			Boolean[] isComplete) {
		this.dateString = ResolutionUtils.convertDateToKey(date);
		this.date = date;
		this.taskDescription = taskDescription;
		this.isComplete = isComplete;
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

	public void setTaskDescription(String[] taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String[] getTaskDescription() {
		return taskDescription;
	}

	public Boolean[] getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean[] isComplete) {
		this.isComplete = isComplete;
	}

	public void addTask(String newTask, Boolean isComplete) {
		String[] newTasks = new String[this.taskDescription.length + 1];
		for (int i = 0; i < this.taskDescription.length; i++) {
			newTasks[i] = this.taskDescription[i];
		}
		newTasks[newTasks.length - 1] = newTask;
		this.taskDescription = newTasks;

		Boolean[] newTaskIsComplete = new Boolean[this.isComplete.length + 1];
		for (int i = 0; i < this.isComplete.length; i++) {
			newTaskIsComplete[i] = this.isComplete[i];
		}
		newTaskIsComplete[newTaskIsComplete.length - 1] = isComplete;
		this.isComplete = newTaskIsComplete;
	}

	public void removeTask(String newTask) {
		int removeAtIndex = -1;
		for (int i = 0; i < this.taskDescription.length; i++) {
			if (this.taskDescription[i].equals(newTask))
				removeAtIndex = i;
		}
		if (removeAtIndex == -1)
			return;

		String[] newTasks = new String[this.taskDescription.length - 1];
		for (int i = 0; i < this.taskDescription.length; i++) {
			if (i < removeAtIndex)
				newTasks[i] = this.taskDescription[i];
			else if (i > removeAtIndex)
				newTasks[i - 1] = this.taskDescription[i];
		}
		this.taskDescription = newTasks;

		Boolean[] newTaskIsComplete = new Boolean[this.isComplete.length - 1];
		for (int i = 0; i < this.isComplete.length; i++) {
			if (i < removeAtIndex)
				newTaskIsComplete[i] = this.isComplete[i];
			else if (i > removeAtIndex)
				newTaskIsComplete[i - 1] = this.isComplete[i];
		}
		this.isComplete = newTaskIsComplete;
	}

	public void updateTask(String task, Boolean isComplete) {
		for (int i = 0; i < this.taskDescription.length; i++) {
			if (this.taskDescription[i].equals(task))
			{
				this.isComplete[i] = isComplete;
			}			
		}
	}
}
