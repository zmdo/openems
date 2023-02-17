package com.schenchi.ems.platform.application;

public class ApplicationConfigure {
	
	private String applicationName ;
	private String applicationLogo ;
	private String applicationViewsPackage;
	private boolean fullScreen;
	private int windowWidth;
	private int windowHeight;
	
	public String getApplicationLogo() {
		return applicationLogo;
	}

	public void setApplicationLogo(String applicationLogo) {
		this.applicationLogo = applicationLogo;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public String getApplicationViewsPackage() {
		return applicationViewsPackage;
	}

	public void setApplicationViewsPackage(String applicationViewsPackage) {
		this.applicationViewsPackage = applicationViewsPackage;
	}

}
