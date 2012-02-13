package com.bobvash.resolutiontracker.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.taskdefs.Taskdef;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResolutionDayWidget extends VerticalPanel {
	private Date date;
	private int row;
	private int col;
	private final List<TaskListClientView> loadedTaskList = new ArrayList<TaskListClientView>();
	private final VerticalPanel tasksHolderPanel = new VerticalPanel();

	private final TaskManagementServiceAsync taskManagementService = GWT
			.create(TaskManagementService.class);

	public ResolutionDayWidget(final Date date, final int row, final int col) {
		this.date = date;
		this.row = row;
		this.col = col;
		this.setSize("150px", "100px");
		this.setSpacing(5);

		HorizontalPanel headerHolderPanel = new HorizontalPanel();
		Label dayLabel = new Label("" + date.getDate());
		headerHolderPanel.add(dayLabel);
		dayLabel.setWidth("50px");

		Button newTaskButton = new Button("+");
		newTaskButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				DialogBox dialogBox = createNewTaskDialogBox();

		          Widget source = (Widget) event.getSource();
		          int left = source.getAbsoluteLeft() + 10;
		          int top = source.getAbsoluteTop() + 10;
		          if (col == 5)
		        	  left = left - 50;
	        	  if (col == 6) 
	        		  left = left - 200;
	        	  if (row ==  5)
	        		  top = top - 100;
	        	  if (row == 6)
	        		  top = top - 200;	        	  
		          dialogBox.setPopupPosition(left, top);
		          dialogBox.show();
//				final String newTaskTitle = Window.prompt(
//						"What will the new task be titled?", "");
//				final String newTaskDescription = Window.prompt(
//						"What will be the description?", "");
//				final String owner = Window.prompt(
//						"Who will this belong to?", "");
//				if (newTaskTitle.length() > 0) //TODO NOT ASSUME FIRST
//				{
//					if (loadedTaskList.size() > 0) {
//						taskManagementService.addTaskToList(loadedTaskList.get(0), new SingleTaskClientView(newTaskTitle, newTaskDescription, owner, false), 
//							new AsyncCallback<Void>() {
//
//								@Override
//								public void onFailure(Throwable error) {
//									Window.alert("Creation of new task failed: " + error.getMessage());
//								}
//
//								@Override
//								public void onSuccess(Void ignore) {
//									loadTasksForDay();
//								}
//							});
//
//					}
//					else {
//						taskManagementService.saveTaskList( new TaskListClientView(date, new SingleTaskClientView[]{new SingleTaskClientView(newTaskTitle, newTaskDescription, owner, false)}),  
//								new AsyncCallback<Void>() {
//
//									@Override
//									public void onFailure(Throwable error) {
//										Window.alert("Creation of new task failed: " + error.getMessage());
//									}
//
//									@Override
//									public void onSuccess(Void ignore) {
//										loadTasksForDay();
//									}
//								});
//						
//					}
//				}
			}
		});
		newTaskButton.setHeight("20px");
		newTaskButton.setStyleName("dayWidgetOutOfRange");
		headerHolderPanel.add(newTaskButton);

		this.add(headerHolderPanel);
		loadTasksForDay();
		
		this.add(tasksHolderPanel);
	}

	private void loadTasksForDay() {
		taskManagementService.getTasksForDate(date,
				new AsyncCallback<TaskListClientView>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Loading of tasks failed: " + caught.getMessage());
					}

					@Override
					public void onSuccess(TaskListClientView result) {

						while(tasksHolderPanel.getWidgetCount() > 0)
							tasksHolderPanel.remove(tasksHolderPanel.getWidget(0));
						
						loadedTaskList.clear();
						if (result != null) 
						{
							loadedTaskList.add(result);
							for (final TaskListClientView taskList: loadedTaskList)
								for (final SingleTaskClientView task : taskList.getTasks()) {
									createNewTaskWidget(taskList, task);
							}
						}
					}
				});
	}
	
	private void createNewTaskWidget(final TaskListClientView currentTaskList, final SingleTaskClientView task) {
	    final DialogBox dialogBox = createTaskEditDialogBox(currentTaskList, task);
	    
		HorizontalPanel taskRowPanel = new HorizontalPanel();

		Button taskButton = new Button("SUP SUP", new ClickHandler() {
		        public void onClick(ClickEvent event) {
			          Widget source = (Widget) event.getSource();
			          int left = source.getAbsoluteLeft() + 10;
			          int top = source.getAbsoluteTop() + 10;
			          if (col == 5)
			        	  left = left - 50;
		        	  if (col == 6) 
		        		  left = left - 200;
		        	  if (row ==  5)
		        		  top = top - 100;
		        	  if (row == 6)
		        		  top = top - 200;	  
			          dialogBox.setPopupPosition(left, top);
			          dialogBox.show();
		        }
		      });
		
		taskButton.setText(task.getTitle());
		taskButton.setWidth("130px");
		taskRowPanel.add(taskButton);

		final PushButton taskStatusCompleteButton = new PushButton(new Image(
				"resources/complete.png"));
		final PushButton taskStatusIncompleteButton = new PushButton(new Image(
				"resources/incomplete.png"));

		taskStatusCompleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				taskManagementService.updateTaskStatus(currentTaskList, task, false,
						
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Update of tasks failed: " + caught.getMessage());
							}

							@Override
							public void onSuccess(Void ignore) {
								loadTasksForDay();
							}
						});
			}
		});
		taskStatusIncompleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				taskManagementService.updateTaskStatus(currentTaskList, task, true,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Update of tasks failed: " + caught.getMessage());
							}

							@Override
							public void onSuccess(Void ignore) {
								loadTasksForDay();
							}
						});
			}
		});
		taskStatusCompleteButton.setVisible(task.isCompleted());
		taskStatusIncompleteButton.setVisible(!task.isCompleted());

		taskRowPanel.add(taskStatusCompleteButton);
		taskRowPanel.add(taskStatusIncompleteButton);

		tasksHolderPanel.add(taskRowPanel);
		
	}

	private DialogBox createTaskEditDialogBox(final TaskListClientView currentTaskList, final SingleTaskClientView task) {
		// Create a dialog box and set the caption text
	    final DialogBox dialogBox = new DialogBox();
	    dialogBox.ensureDebugId("cwDialogBox");
	    dialogBox.setText(task.getTitle());
	    dialogBox.setAutoHideEnabled(true);
	    dialogBox.setGlassEnabled(true);
	    dialogBox.setAnimationEnabled(true);

	    // Create a table to layout the content
	    VerticalPanel dialogContents = new VerticalPanel();
	    dialogContents.setSize("300px", "200px");
	    dialogContents.setSpacing(4);
	    dialogBox.setWidget(dialogContents);

	    Label titleLabel = new Label("Title: ");
	    dialogContents.add(titleLabel);
	    
	    final TextBox titleEditTextBox = new TextBox();
	    titleEditTextBox.setTitle("Title");
	    titleEditTextBox.setText(task.getTitle());
	    titleEditTextBox.setWidth("280px");
	    dialogContents.add(titleEditTextBox);
	    
	    Label descriptionLabel = new Label("Description: ");
	    dialogContents.add(descriptionLabel);
	    
	    final TextArea descriptionEditTextArea = new TextArea();
	    descriptionEditTextArea.setTitle("Description");
	    descriptionEditTextArea.setText(task.getDescription());
	    descriptionEditTextArea.setWidth("280px");
	    dialogContents.add(descriptionEditTextArea);
	    	    
	    Label ownerLabel = new Label("Owner: ");
	    dialogContents.add(ownerLabel);
	    
	    // Add a drop box with the list of users
	    final ListBox dropBox = new ListBox(false);
	      dropBox.addItem("Bobby");
	      dropBox.addItem("Sela");
	     for (int i=0; i<dropBox.getItemCount(); i++)
	    	 if (dropBox.getItemText(i).equals(task.getOwner()))
	    		 dropBox.setSelectedIndex(i);
	    dropBox.setWidth("100px");
	    dialogContents.add(dropBox);
	    
	    HorizontalPanel bottomContainer = new HorizontalPanel();
	    bottomContainer.setWidth("100%");

	    Button saveButton = new Button(
	        "Save", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  if (!titleEditTextBox.getText().equals(task.getTitle()))
					taskManagementService.updateTaskTitle(currentTaskList, task, titleEditTextBox.getText(),
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable error) {
									Window.alert("Update of task title failed: " + error.getMessage());
								}

								@Override
								public void onSuccess(Void ignore) {
									loadTasksForDay();
								}
							});
	        	  if (!descriptionEditTextArea.getText().equals(task.getDescription()))
						taskManagementService.updateTaskDescription(currentTaskList, task, descriptionEditTextArea.getText(),
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable error) {
										Window.alert("Update of task description failed: " + error.getMessage());
									}

									@Override
									public void onSuccess(Void ignore) {
										loadTasksForDay();
									}
								});
	        	  if (!dropBox.getItemText(dropBox.getSelectedIndex()).equals(task.getOwner()))
						taskManagementService.updateTaskOwner(currentTaskList, task, dropBox.getItemText(dropBox.getSelectedIndex()),
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable error) {
										Window.alert("Update of task owner failed: " + error.getMessage());
									}

									@Override
									public void onSuccess(Void ignore) {
										loadTasksForDay();
									}
								});
	          }
	        });
	    bottomContainer.add(saveButton);
	    bottomContainer.setCellHorizontalAlignment(saveButton, HasHorizontalAlignment.ALIGN_LEFT);
	    
	    // Add a delete button at the bottom of the dialog
	    Button deleteButton = new Button(
	        "Delete", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	 Boolean confirm = Window.confirm("Are you sure you want to delete this task?");
	        	 if (confirm) {
						taskManagementService.removeFromTasklist(currentTaskList, task,
								new AsyncCallback<Void>() {
	
									@Override
									public void onFailure(Throwable error) {
										Window.alert("Removal of task failed: " + error.getMessage());
									}
	
									@Override
									public void onSuccess(Void ignore) {
						        		dialogBox.hide();
										loadTasksForDay();
									}
								});
	        	 }
	          }
	        });
	    bottomContainer.add(deleteButton);
	    bottomContainer.setCellHorizontalAlignment(deleteButton, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add a close button at the bottom of the dialog
	    Button closeButton = new Button(
	        "Close", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	            dialogBox.hide();
	          }
	        });	  
	    bottomContainer.add(closeButton);
	    bottomContainer.setCellHorizontalAlignment(closeButton, HasHorizontalAlignment.ALIGN_RIGHT);

	    dialogContents.add(bottomContainer);
	    dialogContents.setCellVerticalAlignment(bottomContainer, HasVerticalAlignment.ALIGN_BOTTOM);
	    
	    // Return the dialog box
	    return dialogBox;
	}
	
	private DialogBox createNewTaskDialogBox() {
		// Create a dialog box and set the caption text
	    final DialogBox dialogBox = new DialogBox();
	    dialogBox.ensureDebugId("cwDialogBox");
	    dialogBox.setText("New Task");
	    dialogBox.setAutoHideEnabled(true);
	    dialogBox.setGlassEnabled(true);
	    dialogBox.setAnimationEnabled(true);

	    // Create a table to layout the content
	    VerticalPanel dialogContents = new VerticalPanel();
	    dialogContents.setSize("300px", "200px");
	    dialogContents.setSpacing(4);
	    dialogBox.setWidget(dialogContents);

	    Label titleLabel = new Label("Title: ");
	    dialogContents.add(titleLabel);
	    
	    final TextBox titleEditTextBox = new TextBox();
	    titleEditTextBox.setTitle("Title");
	    titleEditTextBox.setText("");
	    titleEditTextBox.setWidth("280px");
	    dialogContents.add(titleEditTextBox);
	    
	    Label descriptionLabel = new Label("Description: ");
	    dialogContents.add(descriptionLabel);
	    
	    final TextArea descriptionEditTextArea = new TextArea();
	    descriptionEditTextArea.setTitle("Description");
	    descriptionEditTextArea.setText("");
	    descriptionEditTextArea.setWidth("280px");
	    dialogContents.add(descriptionEditTextArea);
	    	    
	    Label ownerLabel = new Label("Owner: ");
	    dialogContents.add(ownerLabel);
	    
	    // Add a drop box with the list of users
	    final ListBox dropBox = new ListBox(false);
	      dropBox.addItem("Bobby");
	      dropBox.addItem("Sela");
	    dropBox.setSelectedIndex(0);
	    dropBox.setWidth("100px");
	    dialogContents.add(dropBox);
	    
	    HorizontalPanel bottomContainer = new HorizontalPanel();
	    bottomContainer.setWidth("100%");

	    Button saveButton = new Button(
	        "Save", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  
	        	  if (!titleEditTextBox.getText().equals("")) {
	        		  String newTitle = titleEditTextBox.getText();
	        		  String newDescription = descriptionEditTextArea.getText();
	        		  String owner = dropBox.getItemText(dropBox.getSelectedIndex());
	        		  
	        		  //verify title is unique
	        		  for (TaskListClientView taskList : loadedTaskList) {
		        		  for (SingleTaskClientView task : taskList.getTasks()) {
		        			  if (task.getTitle().equals(newTitle)) {
		  						  Window.alert("Title must be unique. '" + task.getTitle() + "' already exists.");
		        				  return;
		        			  }
		        		  }
	        		  }
	        		  
						if (loadedTaskList.size() > 0) {
							taskManagementService.addTaskToList(loadedTaskList.get(0), new SingleTaskClientView(newTitle, newDescription, owner, false), 
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable error) {
										Window.alert("Creation of new task failed: " + error.getMessage());
									}

									@Override
									public void onSuccess(Void ignore) {
										loadTasksForDay();
							            dialogBox.hide();
									}
								});

						}
						else {
							taskManagementService.saveTaskList( new TaskListClientView(date, new SingleTaskClientView[]{new SingleTaskClientView(newTitle, newDescription, owner, false)}),  
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable error) {
											Window.alert("Creation of new task failed: " + error.getMessage());
										}

										@Override
										public void onSuccess(Void ignore) {
											loadTasksForDay();
								            dialogBox.hide();
										}
									});
							
						}
	        	  }
	        	  else {
						Window.alert("Title cannot be empty.");
	        	  }
	          }
	        });
	    bottomContainer.add(saveButton);
	    bottomContainer.setCellHorizontalAlignment(saveButton, HasHorizontalAlignment.ALIGN_LEFT);
	    
	    // Add a close button at the bottom of the dialog
	    Button closeButton = new Button(
	        "Cancel", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	            dialogBox.hide();
	          }
	        });	  
	    bottomContainer.add(closeButton);
	    bottomContainer.setCellHorizontalAlignment(closeButton, HasHorizontalAlignment.ALIGN_RIGHT);

	    dialogContents.add(bottomContainer);
	    dialogContents.setCellVerticalAlignment(bottomContainer, HasVerticalAlignment.ALIGN_BOTTOM);
	    
	    // Return the dialog box
	    return dialogBox;
	}
}
