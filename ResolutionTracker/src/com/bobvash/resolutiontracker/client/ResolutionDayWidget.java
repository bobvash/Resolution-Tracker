package com.bobvash.resolutiontracker.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResolutionDayWidget extends VerticalPanel {
	public ResolutionDayWidget(int dayNumber) {
		this.setSize("100px", "100px");
		this.setSpacing(5);
		Label dayLabel = new Label(""+dayNumber);
		this.add(dayLabel);
	}
}
