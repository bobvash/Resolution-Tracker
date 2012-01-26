package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TaskManagementServiceAsync {
	public void getTasksForDate(Date date, AsyncCallback<TasksClientView> callback);

	public void addTaskForDate(Date date, String task, AsyncCallback<Void> callback);
	
	public void removeTaskForDate(Date date, String task, AsyncCallback<Void> callback);
}