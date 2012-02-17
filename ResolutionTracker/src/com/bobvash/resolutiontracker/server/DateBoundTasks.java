package com.bobvash.resolutiontracker.server;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.bobvash.resolutiontracker.client.ResolutionUtils;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class DateBoundTasks {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String dateString;

	@Persistent
	private String[] taskTitle;

	@Persistent
	private String[] taskDescription;

	@Persistent
	private String[] taskOwner;

	@Persistent
	private Boolean[] isComplete;

	public DateBoundTasks(String dateKey, String[] taskTitle,
			String[] taskDescription, String[] taskOwner, Boolean[] isComplete) {
		this.dateString = dateKey;
		this.taskTitle = taskTitle;
		this.taskDescription = taskDescription;
		this.taskOwner = taskOwner;
		this.isComplete = isComplete;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getDateString() {
		return dateString;
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

	public String[] getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(String[] taskOwner) {
		this.taskOwner = taskOwner;
	}

	public void addTask(String newTaskTitle, String newTaskDescription,
			String owner, Boolean isComplete) {

		String[] newTaskTitles = new String[this.isComplete.length + 1];
		for (int i = 0; i < this.isComplete.length; i++) {
			newTaskTitles[i] = this.taskTitle[i];
		}
		newTaskTitles[newTaskTitles.length - 1] = newTaskTitle;
		this.taskTitle = newTaskTitles;

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

		String[] newTaskOwner = new String[this.taskOwner.length + 1];
		for (int i = 0; i < this.taskOwner.length; i++) {
			newTaskOwner[i] = this.taskOwner[i];
		}
		newTaskOwner[newTaskOwner.length - 1] = owner;
		this.taskOwner = newTaskOwner;
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
		
		String[] newTaskOwners = new String[this.taskOwner.length - 1];
		for (int i = 0; i < this.taskOwner.length; i++) {
			if (i < removeAtIndex)
				newTaskOwners[i] = this.taskOwner[i];
			else if (i > removeAtIndex)
				newTaskOwners[i - 1] = this.taskOwner[i];
		}
		this.taskOwner = newTaskOwners;
	}

	public void updateTaskStatus(String forTaskTitle, Boolean isComplete) {
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(forTaskTitle)) {
				this.isComplete[i] = isComplete;
			}
		}
	}

	public void updateTaskDescription(String forTaskTitle, String description) {
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(forTaskTitle)) {
				this.taskDescription[i] = description;
			}
		}
	}

	public void updateTaskTitle(String forTaskTitle, String newTitle) {
		for (int i = 0; i < this.taskTitle.length; i++) {
			if (this.taskTitle[i].equals(forTaskTitle)) {
				this.taskTitle[i] = newTitle;
			}
		}
	}
	
	public void updateTaskOwner(String forTaskTitle, String newOwner) {
		for (int i = 0; i < this.taskOwner.length; i++) {
			if (this.taskTitle[i].equals(forTaskTitle)) {
				this.taskOwner[i] = newOwner;
			}
		}
	}
}
