package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("taskmanagement")
public interface TaskManagementService extends RemoteService {
	public TaskListClientView getTasksForDate(Date date);

	public void saveTaskList(TaskListClientView taskList);

	public void updateTaskStatus(TaskListClientView taskList, SingleTaskClientView task, boolean isComplete);

	public void addTaskToList(TaskListClientView taskList, SingleTaskClientView newTask);

	public void removeFromTasklist(TaskListClientView taskList,	SingleTaskClientView newTask);
}