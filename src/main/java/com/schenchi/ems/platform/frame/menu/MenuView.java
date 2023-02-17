package com.schenchi.ems.platform.frame.menu;

import com.tricolorfire.labfx.control.MenuTabPane;

import javafx.scene.layout.StackPane;

/**
 * 菜单界面
 * 仿 MATLAB 2019a 的菜单界面
 */
public class MenuView extends StackPane {
	
	// 菜单
	private MenuTabPane menu;
	// 通用操作栏
	private CommonActionBar commonActionBar;
	
	public MenuView() {
		this(new MenuTabPane());
	}
	
	public MenuView(MenuTabPane menu) {
		this(menu,new CommonActionBar(menu));
	}
	
	public MenuView(MenuTabPane menu,CommonActionBar commonActionBar) {
		this.menu = menu;
		this.commonActionBar = commonActionBar;
		this.getChildren().addAll(menu,commonActionBar);
	}
	
	public MenuTabPane getMenu() {
		return menu;
	}
	
	public CommonActionBar getCommonActionBar() {
		return commonActionBar;
	}
	
}
