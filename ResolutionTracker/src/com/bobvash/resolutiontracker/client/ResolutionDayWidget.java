package com.bobvash.resolutiontracker.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResolutionDayWidget extends VerticalPanel {
	public ResolutionDayWidget(int dayNumber) {
		this.add(new Label(""+dayNumber));
	}
}
