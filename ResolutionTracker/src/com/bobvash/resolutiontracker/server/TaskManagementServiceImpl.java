package com.bobvash.resolutiontracker.server;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.bobvash.resolutiontracker.client.TaskManagementService;
import com.bobvash.resolutiontracker.client.TasksClientView;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TaskManagementServiceImpl extends RemoteServiceServlet implements
		TaskManagementService {

	private static final PersistenceManagerFactory PMF = PMFSingleton
			.getInstance();

	public TasksClientView getTasksForDate(Date date) {
		PersistenceManager pm = getPersistenceManager();
		TasksClientView result = null;
		try {
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(ResolutionUtils.convertDateToKey(date));
			if (tasks.size() == 0)
				return new TasksClientView(date, new String[] {});
			result = new TasksClientView(date, tasks.get(0).getTasks());
		} finally {
			pm.close();
		}
		return result;
	}

	public void addTaskForDate(Date date, String task) {
		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(ResolutionUtils.convertDateToKey(date));
			if (tasks.size() != 0)
				result = tasks.get(0);

			if (result == null) {
				String[] taskList = { task };
				result = new DateBoundTasks(date, taskList);
			} else {
				result.addTask(task);
			}

			pm.makePersistent(result);
		} finally {
			pm.close();
		}
	}

	public void removeTaskForDate(Date date, String task) {
		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(ResolutionUtils.convertDateToKey(date));
			if (tasks.size() != 0)
				result = tasks.get(0);

			if (result != null) {
				result.removeTask(task);
				pm.makePersistent(result);
			}

		} finally {
			pm.close();
		}
	}
	
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}
}
