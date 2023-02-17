package com.schenchi.ems.platform.service;

import com.schenchi.ems.platform.frame.statusbar.StatusBar;

public class StatusBarService {

	private StatusBar statusBar;

	public StatusBarService() {
		statusBar = new StatusBar();
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
}
