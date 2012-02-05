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
	private String[] taskTitle;

	@Persistent
	private String[] taskDescription;

	@Persistent
	private Boolean[] isComplete;

	public DateBoundTasks(Date date, String[] taskTitle,
			String[] taskDescription, Boolean[] isComplete) {
		this.dateString = ResolutionUtils.convertDateToKey(date);
		this.date = date;
		this.taskTitle = taskTitle;
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

	public String[] getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String[] taskTitle) {
		this.taskTitle = taskTitle;
	}

	public void addTask(String newTaskTitle, String newTaskDescription, Boolean isComplete) {

		Boolean[] newTaskTitles = new Boolean[this.isComplete.length + 1];
		for (int i = 0; i < this.isComplete.length; i++) {
			newTaskTitles[i] = this.isComplete[i];
		}
		newTaskTitles[newTaskTitles.length - 1] = isComplete;
		this.isComplete = newTaskTitles;
		
		String[] newTasksDesc = new String[this.taskDescription.length + 1];
		for (int i = 0; i < this.taskDescription.length; i++) {
			newTasksDesc[i] = this.taskDescription[i];
		}
		newTasksDesc[newTasksDesc.length - 1] = newTaskDescription;
		this.taskDescription = newTasksDesc;
		
		Boolean[] newTaskIsComplete = new Boolean[this.isComplete.length + 1];
		for (int i = 0; i < this.isComplete.length; i++) {
			newTaskIsComplete[i] = this.isComplete[i];
		}
		newTaskIsComplete[newTaskIsComplete.length - 1] = isComplete;
		this.isComplete = newTaskIsComplete;
	}

	public void removeTask(String newTaskTitle) {
		int removeAtIndex = -1;
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(newTaskTitle))
				removeAtIndex = i;
		}
		if (removeAtIndex == -1)
			return;

		String[] newTasks = new String[this.taskTitle.length - 1];
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (i < removeAtIndex)
				newTasks[i] = this.taskTitle[i];
			else if (i > removeAtIndex)
				newTasks[i - 1] = this.taskTitle[i];
		}
		this.taskTitle = newTasks;
		
		String[] newTaskDescriptions = new String[this.taskDescription.length - 1];
		for (int i = 0; i < this.taskDescription.length; i++) {
			if (i < removeAtIndex)
				newTaskDescriptions[i] = this.taskDescription[i];
			else if (i > removeAtIndex)
				newTaskDescriptions[i - 1] = this.taskDescription[i];
		}
		this.taskDescription = newTaskDescriptions;

		Boolean[] newTaskIsComplete = new Boolean[this.isComplete.length - 1];
		for (int i = 0; i < this.isComplete.length; i++) {
			if (i < removeAtIndex)
				newTaskIsComplete[i] = this.isComplete[i];
			else if (i > removeAtIndex)
				newTaskIsComplete[i - 1] = this.isComplete[i];
		}
		this.isComplete = newTaskIsComplete;
	}

	public void updateTaskStatus(String task, Boolean isComplete) {
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(task)) {
				this.isComplete[i] = isComplete;
			}
		}
	}

	public void updateTaskDescription(String task, String description) {
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(task)) {
				this.taskDescription[i] = description;
			}
		}
	}
	
	public void updateTaskTitle(String task, String newTitle) {
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(task)) {
				this.taskTitle[i] = newTitle;
			}
		}
	}
}
