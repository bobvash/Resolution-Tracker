package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TaskManagementServiceAsync {
	public void getTasksForDate(String dateKey, AsyncCallback<TaskListClientView> callback);

	public void saveTaskList(TaskListClientView taskList, AsyncCallback<Void> callback);

	public void updateTaskStatus(TaskListClientView taskList, SingleTaskClientView task, boolean isComplete, AsyncCallback<Void> callback);
	
	public void updateTaskTitle(TaskListClientView taskList, SingleTaskClientView task, String newTitle, AsyncCallback<Void> callback);
	
	public void updateTaskDescription(TaskListClientView taskList, SingleTaskClientView task, String description, AsyncCallback<Void> callback);
	
	public void updateTaskOwner(TaskListClientView taskList, SingleTaskClientView task, String newOwner, AsyncCallback<Void> callback);

	public void addTaskToList(TaskListClientView taskList, SingleTaskClientView newTask, AsyncCallback<Void> callback);

	public void removeFromTasklist(TaskListClientView taskList,	SingleTaskClientView newTask, AsyncCallback<Void> callback);
}