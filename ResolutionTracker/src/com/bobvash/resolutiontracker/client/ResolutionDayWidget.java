package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResolutionDayWidget extends VerticalPanel {
	private Date date;

	private final TaskManagementServiceAsync taskManagementService = GWT
			.create(TaskManagementService.class);

	public ResolutionDayWidget(final Date date) {
		this.date = date;
		this.setSize("100px", "100px");
		this.setSpacing(5);

		final VerticalPanel tasksHolderPanel = new VerticalPanel();

		HorizontalPanel headerHolderPanel = new HorizontalPanel();
		Label dayLabel = new Label("" + date.getDate());
		headerHolderPanel.add(dayLabel);
		dayLabel.setWidth("50px");

		Button newTaskButton = new Button("+");
		newTaskButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final String newTask = Window.prompt(
						"What will the new task be?", "");
				if (newTask.length() > 0)
					taskManagementService.addTaskForDate(date, newTask,
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable error) {
									Window.alert("Creation of new task failed: " + error.getMessage());
								}

								@Override
								public void onSuccess(Void ignore) {
									tasksHolderPanel.add(new Label(newTask));
								}
							});
			}
		});
		newTaskButton.setHeight("20px");
		newTaskButton.setStyleName("dayWidgetOutOfRange");
		headerHolderPanel.add(newTaskButton);
		
		Button removeTaskButtion = new Button("-");
		removeTaskButtion.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final String newTask = Window.prompt(
						"Which task to remove?", "");
				if (newTask.length() > 0)
					taskManagementService.removeTaskForDate(date, newTask,
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable error) {
									Window.alert("Removal of task failed: " + error.getMessage());
								}

								@Override
								public void onSuccess(Void ignore) {
									loadTasksForDay(tasksHolderPanel);
								}
							});
			}
		});
		removeTaskButtion.setHeight("20px");
		removeTaskButtion.setStyleName("dayWidgetOutOfRange");
		headerHolderPanel.add(removeTaskButtion);

		this.add(headerHolderPanel);
		loadTasksForDay(tasksHolderPanel);
		
		this.add(tasksHolderPanel);
	}

	private void loadTasksForDay(final VerticalPanel tasksHolderPanel) {
		while(tasksHolderPanel.getWidgetCount() > 0)
			tasksHolderPanel.remove(tasksHolderPanel.getWidget(0));
		
		taskManagementService.getTasksForDate(date,
				new AsyncCallback<TasksClientView>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Loading of tasks failed: " + caught.getMessage());
					}

					@Override
					public void onSuccess(TasksClientView result) {
						for (String task : result.getTasks()) {
							tasksHolderPanel.add(new Label(task));
						}
					}
				});
	}
}
