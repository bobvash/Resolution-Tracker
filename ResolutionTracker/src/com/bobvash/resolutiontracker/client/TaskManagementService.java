package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("taskmanagement")
public interface TaskManagementService extends RemoteService {
	public TasksClientView getTasksForDate(Date date);

	public void addTaskForDate(Date date, String task);
	
	public void removeTaskForDate(Date date, String task);
}