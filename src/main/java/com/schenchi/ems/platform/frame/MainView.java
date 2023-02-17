package com.schenchi.ems.platform.frame;

import com.schenchi.ems.platform.EmsPlatform;

import com.schenchi.ems.platform.frame.menu.MenuView;
import com.schenchi.ems.platform.frame.statusbar.StatusBar;
import com.tricolorfire.labfx.dockFX.DockPane;

import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
	
	// 菜单栏
	private MenuView menu;
	
	// 可停靠面板
	private DockPane dockPane;
	
	// 状态栏
	private StatusBar statusBar;

	public MainView() {
		init();
	}
	
	private void init() {
		initMenu();
		initDockPane();
		initStatusBar();
	}

	private void initDockPane() {
		 dockPane = EmsPlatform.getService().getDockService().getDockPane();
		 setCenter(dockPane);
	}

	// 初始化状态栏
	private void initStatusBar() {
		
    	// 状态栏
    	statusBar = EmsPlatform.getService().getStatusBarService().getStatusBar();
    	setBottom(statusBar);
    	
	}

	/**
	 * 初始化菜单栏
	 */
	private void initMenu() {
		// 菜单
    	menu = EmsPlatform.getService().getMenuTabService().getMenu();
    	// 设置菜单顶端
    	setTop(menu);
	}
	
}
