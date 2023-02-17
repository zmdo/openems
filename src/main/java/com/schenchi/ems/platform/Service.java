package com.schenchi.ems.platform;

import com.schenchi.ems.platform.service.DataService;
import com.schenchi.ems.platform.service.DockService;
import com.schenchi.ems.platform.service.EditorService;
import com.schenchi.ems.platform.service.MenuTabService;
import com.schenchi.ems.platform.service.StatusBarService;

public class Service {
	
	public static final Service INSTANCE = new Service();
	
	private MenuTabService menuTabService = new MenuTabService();
	private DataService globalDataService = new DataService();
	private EditorService editorService = new EditorService();
	private DockService dockService = new DockService();
	private StatusBarService statusBarService = new StatusBarService();
	
	private Service() {}
	
	public MenuTabService getMenuTabService() {
		return menuTabService;
	}
	
	public DataService getGlobalDataService() {
		return globalDataService;
	}
	
	public EditorService getEditorService() {
		return editorService;
	}

	public DockService getDockService() {
		return dockService;
	}

	public StatusBarService getStatusBarService() {
		return statusBarService;
	}
	
}
