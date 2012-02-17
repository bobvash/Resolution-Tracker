package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("taskmanagement")
public interface TaskManagementService extends RemoteService {
	public TaskListClientView getTasksForDate(String dateKey);

	public void saveTaskList(TaskListClientView taskList);

	public void updateTaskStatus(TaskListClientView taskList, SingleTaskClientView task, boolean isComplete);

	public void updateTaskTitle(TaskListClientView taskList, SingleTaskClientView task, String newTitle);
	
	public void updateTaskDescription(TaskListClientView taskList, SingleTaskClientView task, String newDescription);
	
	public void updateTaskOwner(TaskListClientView taskList, SingleTaskClientView task, String newOwner);

	public void addTaskToList(TaskListClientView taskList, SingleTaskClientView newTask);

	public void removeFromTasklist(TaskListClientView taskList,	SingleTaskClientView newTask);
}