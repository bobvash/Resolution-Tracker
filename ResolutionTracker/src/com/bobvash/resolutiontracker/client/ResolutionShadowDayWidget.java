package com.bobvash.resolutiontracker.client;

import java.util.Date;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResolutionShadowDayWidget extends VerticalPanel {
	private Date date;

	public ResolutionShadowDayWidget(Date date) {
		this.date = date;

		this.setSize("100px", "100px");
		this.setSpacing(5);
		Label dayLabel = new Label("" + date.getDate());
		dayLabel.setStyleName("dayWidgetOutOfRange");
		this.add(dayLabel);
	}
}
