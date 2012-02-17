package com.bobvash.resolutiontracker.server;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.bobvash.resolutiontracker.client.ResolutionUtils;
import com.bobvash.resolutiontracker.client.SingleTaskClientView;
import com.bobvash.resolutiontracker.client.TaskListClientView;
import com.bobvash.resolutiontracker.client.TaskManagementService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TaskManagementServiceImpl extends RemoteServiceServlet implements
		TaskManagementService {

	private static final PersistenceManagerFactory PMF = PMFSingleton
			.getInstance();

	public TaskListClientView getTasksForDate(String dateKey) {
		PersistenceManager pm = getPersistenceManager();
		TaskListClientView result = null;
		try {
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");
			
			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(dateKey);
			if (tasks.size() == 0 || tasks.get(0).getTaskTitle().length == 0)
				return null;
			
			DateBoundTasks taskInPersistantView = tasks.get(0);

			int taskListLength = Math.max(Math.max(
					taskInPersistantView.getTaskDescription().length,
					taskInPersistantView.getTaskTitle().length), Math.max(
					taskInPersistantView.getIsComplete().length,
					taskInPersistantView.getTaskOwner().length));

			SingleTaskClientView[] clientTasks = new SingleTaskClientView[taskListLength];
				for (int i=0; i<taskListLength; i++)  {
						clientTasks[i] = new SingleTaskClientView(
								taskInPersistantView.getTaskTitle().length > i ? taskInPersistantView.getTaskTitle()[i] : ("TITLE MISSING # " + i), 
								taskInPersistantView.getTaskDescription().length > i ? taskInPersistantView.getTaskDescription()[i] : "none", 
								taskInPersistantView.getTaskOwner().length > i ? taskInPersistantView.getTaskOwner()[i] : "Bobby", 
								taskInPersistantView.getIsComplete().length > i ? taskInPersistantView.getIsComplete()[i] : false);
				}
			result = new TaskListClientView(dateKey, clientTasks);

			//To restore data integrity, re-save loaded list with the defaults applied
			saveTaskList(result);
		} finally {
			pm.close();
		}
		return result;
	}
	
	private PersistenceManager getPersistenceManager() {
		return PMF.getPersistenceManager();
	}

	@Override
	public void saveTaskList(TaskListClientView taskList) {
		PersistenceManager pm = getPersistenceManager();
		try {
			String[] tasks = new String[taskList.getTasks().length];
			String[] taskdescriptions = new String[taskList.getTasks().length];
			String[] owners = new String[taskList.getTasks().length];
			Boolean[] completeness = new Boolean[taskList.getTasks().length];
			for (int i=0; i<taskList.getTasks().length; i++)
			{
				tasks[i] = taskList.getTasks()[i].getTitle();
				taskdescriptions[i] = taskList.getTasks()[i].getDescription();
				owners[i] = taskList.getTasks()[i].getOwner();
				completeness[i] = taskList.getTasks()[i].isCompleted();
			}
			DateBoundTasks saveTask = new DateBoundTasks(taskList.getDateString(), tasks, taskdescriptions, owners, completeness);
			
			pm.makePersistent(saveTask);
		} finally {
			pm.close();
		}
	}

	@Override
	public void updateTaskStatus(TaskListClientView taskList,
			SingleTaskClientView task, boolean isComplete) {

		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(taskList.getDateString());
			if (tasks.size() != 0) {
				result = tasks.get(0);
				
				DateBoundTasks detachedResult = pm.detachCopy(result);
				pm.deletePersistent(result);
				detachedResult.updateTaskStatus(task.getTitle(), isComplete);
				pm.makePersistent(detachedResult);
			}
			
		} finally {
			pm.close();
		}
		
	}

	@Override
	public void updateTaskTitle(TaskListClientView taskList,
			SingleTaskClientView task, String newTitle) {

		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(taskList.getDateString());
			if (tasks.size() != 0) {
				result = tasks.get(0);
				
				DateBoundTasks detachedResult = pm.detachCopy(result);
				pm.deletePersistent(result);
				detachedResult.updateTaskTitle(task.getTitle(), newTitle);
				pm.makePersistent(detachedResult);
			}
			
		} finally {
			pm.close();
		}
		
	}
	
	@Override
	public void updateTaskDescription(TaskListClientView taskList,
			SingleTaskClientView task, String description) {

		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(taskList.getDateString());
			if (tasks.size() != 0) {
				result = tasks.get(0);
				
				DateBoundTasks detachedResult = pm.detachCopy(result);
				pm.deletePersistent(result);
				detachedResult.updateTaskDescription(task.getTitle(), description);
				pm.makePersistent(detachedResult);
			}
			
		} finally {
			pm.close();
		}
		
	}

	@Override
	public void updateTaskOwner(TaskListClientView taskList,
			SingleTaskClientView task, String newOwner) {

		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(taskList.getDateString());
			if (tasks.size() != 0) {
				result = tasks.get(0);
				
				DateBoundTasks detachedResult = pm.detachCopy(result);
				pm.deletePersistent(result);
				detachedResult.updateTaskOwner(task.getTitle(), newOwner);
				pm.makePersistent(detachedResult);
			}
			
		} finally {
			pm.close();
		}
		
	}

	@Override
	public void addTaskToList(TaskListClientView taskList,
			SingleTaskClientView newTask) {
		
		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(taskList.getDateString());
			if (tasks.size() != 0) {
				result = tasks.get(0);
				
				DateBoundTasks detachedResult = pm.detachCopy(result);
				pm.deletePersistent(result);
				detachedResult.addTask(newTask.getTitle(), newTask.getDescription(), newTask.getOwner(), newTask.isCompleted());
				pm.makePersistent(detachedResult);
			}
			
		} finally {
			pm.close();
		}
		
	}

	@Override
	public void removeFromTasklist(TaskListClientView taskList,
			SingleTaskClientView newTask) {
		
		PersistenceManager pm = getPersistenceManager();
		try {			
			DateBoundTasks result = null;
			Query q = pm.newQuery(DateBoundTasks.class, "dateString == ds");
			q.declareParameters("java.lang.String ds");

			List<DateBoundTasks> tasks = (List<DateBoundTasks>) q.execute(taskList.getDateString());
			if (tasks.size() != 0) {
				result = tasks.get(0);
				
				DateBoundTasks detachedResult = pm.detachCopy(result);
				pm.deletePersistent(result);
				detachedResult.removeTask(newTask.getTitle());
				pm.makePersistent(detachedResult);
			}
			
		} finally {
			pm.close();
		}
		
	}
}
