package com.schenchi.ems.platform.service;

import com.schenchi.ems.platform.frame.menu.MenuView;
import com.schenchi.ems.platform.menu.IMenuTab;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;

public class MenuTabService {
	
	private MenuView menu;
	
	private ObservableMap<IMenuTab, Tab> menuTabMap;
	private ObservableList<IMenuTab> menuTabs;
	
	private ObservableList<Tab> tabs;
	private SingleSelectionModel<Tab> selectionModel;
	
	public MenuTabService() {
		this(new MenuView());
	}
	
	public MenuTabService(MenuView menu) {
		this.menu = menu;
		tabs = menu.getMenu().getTabs();
		selectionModel = menu.getMenu().getSelectionModel();
		
		init();
	}
	
	private void init() {
		initTabMap();
		initMenuTabs();
	}

	private void initTabMap() {
		menuTabMap = FXCollections.observableHashMap();
	}

	private void initMenuTabs() {
		
		menuTabs = FXCollections.observableArrayList();
		
		// 添加一个监听器处理进出
		menuTabs.addListener(new ListChangeListener<IMenuTab>() {
			@Override
			public void onChanged(Change<? extends IMenuTab> c) {
				while(c.next()) {
					var addedList = c.getAddedSubList();
					for (var menuTab : addedList) {
						
						// 创建一个 tab
						var tab = new Tab(menuTab.getName());
						tab.setContent(menuTab.getContent());
						if (menuTab.isTemporary()) {
							tab.getStyleClass().add("tmp-tab");
						}
						
						// 加入tab
						menuTabMap.put(menuTab, tab);
						tabs.add(tab);
						
					}
					
					var removedList = c.getRemoved();
					for (var menuTab : removedList) {
						
						// 获得tab
						var tab = menuTabMap.get(menuTab);
						
						// 移除tab
						menuTabMap.remove(menuTab);
						tabs.remove(tab);
						
					}
					
				}
			}
		});
	}

	/******************/
	
	public ObservableList<IMenuTab> getTabs() {
		return menuTabs;
	}
	
	// 获取tab节点
	public Tab getTabNode(IMenuTab tab) {
		return menuTabMap.get(tab);
	}
	
	public IMenuTab getTab(String name) {
		for (var tab : menuTabs) {
			if (tab.getName().equals(name)) {
				return tab;
			}
		}
		return null;
	}
	
	public void addTab(IMenuTab menuTab) {
		menuTabs.add(menuTab);
	}
	
	public void removeTab(String name) {
		menuTabs.remove(getTab(name));
	}
	
	public void removeTab(IMenuTab menuTab) {
		menuTabs.remove(menuTab);
	}
	
	/*******************/
	
	public SingleSelectionModel<Tab> getSelectionModel() {
		return selectionModel;
	}
	
	public void selectTab(int index) {
		selectionModel.clearAndSelect(index);
	}
	
	public void selectTab(String name) {
		var menuTab = getTab(name);
		var tab = menuTabMap.get(menuTab);
		selectTab(tabs.indexOf(tab));
	}
	
	public IMenuTab getSelectedItem() {
		var tab = selectionModel.getSelectedItem();
		return getTab(tab.getText());
	}
	
	/*******************/
	
	public MenuView getMenu() {
		return menu;
	}
	
}
