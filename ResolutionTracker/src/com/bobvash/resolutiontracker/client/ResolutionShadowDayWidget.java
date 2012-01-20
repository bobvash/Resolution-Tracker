package com.bobvash.resolutiontracker.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResolutionShadowDayWidget extends VerticalPanel {
	public ResolutionShadowDayWidget(int dayNumber) {
		this.setSize("100px", "100px");
		this.setSpacing(5);
		Label dayLabel = new Label(""+dayNumber);
		dayLabel.setStyleName("dayWidgetOutOfRange");
		this.add(dayLabel);
	}
}
