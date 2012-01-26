package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResolutionCalendarWidget extends VerticalPanel {

	public ResolutionCalendarWidget() {

		// Create a grid
		Grid grid = new Grid(7, 7);
		grid.setBorderWidth(1);

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
		Date startOfMonth = new Date();
		startOfMonth.setDate(1);
		int currentMonth = startOfMonth.getMonth();
		Date index = (Date) startOfMonth.clone();
		index.setDate(-index.getDay() + 1);

		for (int row = firstWeekRow; row < numRows; row++) {
			for (int col = 0; col < numColumns; col++) {
				if (index.getMonth() == currentMonth)
					grid.setWidget(row, col, new ResolutionDayWidget(
							(Date) index.clone()));
				else
					grid.setWidget(row, col, new ResolutionShadowDayWidget(
							(Date) index.clone()));
				index.setDate(index.getDate() + 1);
			}
		}

		this.add(grid);
	}
}
