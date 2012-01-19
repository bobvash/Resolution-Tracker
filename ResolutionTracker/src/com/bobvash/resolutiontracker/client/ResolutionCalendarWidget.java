package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResolutionCalendarWidget extends VerticalPanel {

	public ResolutionCalendarWidget() {

		// Create a grid
	    Grid grid = new Grid(7, 7);
	    grid.setBorderWidth(1);
	    grid.setCellPadding(5);
	    
	    int dateTitleRow = 0;
	    int firstWeekRow = 1;
	    
	    int numRows = grid.getRowCount();
	    int numColumns = grid.getColumnCount();
	    
	    // Add the day title row
    	grid.setWidget(dateTitleRow, 0, new Label("Sunday"));
    	grid.setWidget(dateTitleRow, 1, new Label("Monday"));
    	grid.setWidget(dateTitleRow, 2, new Label("Tuesday"));
    	grid.setWidget(dateTitleRow, 3, new Label("Wednesday"));
    	grid.setWidget(dateTitleRow, 4, new Label("Thursday"));
    	grid.setWidget(dateTitleRow, 5, new Label("Friday"));
    	grid.setWidget(dateTitleRow, 6, new Label("Saturday"));
	    
	    // Add day entries to the grid
	    int index = 1;
	    Date startOfMonth = new Date();
	    Date endOfMonth = (Date)startOfMonth.clone();
	    startOfMonth.setDate(1);
	    endOfMonth.setMonth(endOfMonth.getMonth()+1);
	    endOfMonth.setDate(0);
	    
	    for (int row = firstWeekRow; row < numRows; row++) {
	      for (int col = 0; col < numColumns; col++) { 
	    	  if ((row > firstWeekRow || col >= startOfMonth.getDay()) && index <= endOfMonth.getDate())
	    		  grid.setWidget(row, col, new ResolutionDayWidget(index++));
	      }
	    }
	    
	    this.add(grid);
	}
}
