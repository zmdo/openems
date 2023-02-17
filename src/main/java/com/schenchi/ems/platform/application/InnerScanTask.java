package com.schenchi.ems.platform.application;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.schenchi.ems.platform.EmsPlatform;
import com.schenchi.ems.platform.dock.Dockable;
import com.schenchi.ems.platform.dock.IDockable;
import com.schenchi.ems.platform.editor.Editor;
import com.schenchi.ems.platform.editor.IEditor;
import com.schenchi.ems.platform.menu.IMenuFunctionalUnit;
import com.schenchi.ems.platform.menu.IMenuTab;
import com.schenchi.ems.platform.menu.MenuFunctionalUnit;
import com.schenchi.ems.platform.menu.MenuTab;
import com.schenchi.ems.platform.task.ApplicationTask;
import com.schenchi.ems.platform.task.IApplicationTask;
import com.schenchi.ems.platform.utils.ClassScanHandler;
import com.schenchi.ems.platform.utils.ISortable;
import com.schenchi.ems.platform.utils.PackageScanner;
import com.schenchi.ems.views.menu.tabunits.home.HomeOtherUnit;
import com.tricolorfire.labfx.control.MenuUnit;

import javafx.stage.Stage;

/**
 * 内部组件扫描任务
 */
public class InnerScanTask implements IApplicationTask {
	
	private String viewPackageName;
	
	// 扫描的包名
	public InnerScanTask(String applicationViewsPackage) {
		this.viewPackageName = applicationViewsPackage;
	}

	@Override
	public int getOrder() {
		return 0;
	}
	
	private String selectedTab; 
	private List<IMenuTab> tabs;
	private List<IDockable> dockables;
	private Map<String,List<IMenuFunctionalUnit>> meunFunctionUnitMap; 
	
	@Override
	public void before(Stage stage) {
		
		// 创建一个包扫描器
		PackageScanner scanner = new PackageScanner(new ClassScanHandler() {
			@Override
			public void handle(Class<?> clazz) {
				//** 扫描应用任务 **//
				var taskAnno = clazz.getDeclaredAnnotation(ApplicationTask.class);
				if (taskAnno != null) {
					IApplicationTask task = null;
					try {
						task = (IApplicationTask)clazz.getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					ApplicaitonInitializer.addTask(task);
				}
			}
		});
		
		// 扫描
		scanner.scan(viewPackageName);
	}
	
	@Override
	public void after(Stage stage) {
		
		tabs = new ArrayList<>();
		dockables = new ArrayList<>();
		meunFunctionUnitMap = new HashMap<String, List<IMenuFunctionalUnit>>();
		
		// 创建一个包扫描器
		PackageScanner scanner = new PackageScanner(new ClassScanHandler() {
			@Override
			public void handle(Class<?> clazz) {
				
				//** 扫描菜单选项卡 **//
				var tabAnno = clazz.getDeclaredAnnotation(MenuTab.class);
				if (tabAnno != null) {
					
					IMenuTab menuTab = null;
					try {
						menuTab = (IMenuTab)clazz.getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					
					// 检查是否选中
					String tabName = menuTab.getName();
					boolean selected = menuTab.isDefaultSelected();
					if(selected) {
						selectedTab = tabName;
					}
					
					tabs.add(menuTab);
				}

				//** 菜单功能单元 **//
				var menuUnitAnno = clazz.getDeclaredAnnotation(MenuFunctionalUnit.class);
				if (menuUnitAnno != null) {
					
					IMenuFunctionalUnit unit = null;
					try {
						unit = (IMenuFunctionalUnit)clazz.getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					
					String belong = unit.getMenuTabName();
					List<IMenuFunctionalUnit> units ;
					if(meunFunctionUnitMap.containsKey(belong)) {
						units = meunFunctionUnitMap.get(belong);
					} else {
						units = new ArrayList<IMenuFunctionalUnit>();
						meunFunctionUnitMap.put(belong, units);
					}
					units.add(unit);
					
				}
				
				//** 扫描编辑器 **//
				var editorAnno = clazz.getDeclaredAnnotation(Editor.class);
				if (editorAnno != null) {
					IEditor editor = null;
					try {
						editor = (IEditor)clazz.getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					// 加入编辑器
					EmsPlatform.getService().getEditorService().getEidtors().add(editor);
				}
				
				//** 扫描可停靠面板 **//
				var dockAnno = clazz.getDeclaredAnnotation(Dockable.class);
				if (dockAnno != null) {
					IDockable dockable = null;
					try {
						dockable = (IDockable) clazz.getConstructor().newInstance();
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
					dockables.add(dockable);
				}
			}
		});
		
		// 扫描
		scanner.scan(viewPackageName);
		
		// 在菜单中添加单元
		for (var tab: tabs) {
			var tabName = tab.getName();
			if (meunFunctionUnitMap.containsKey(tabName)) {
				var units = meunFunctionUnitMap.get(tabName);
				units.sort(ISortable.COMPARATOR);
				for (var unit : units) {
					MenuUnit menuUnit = new MenuUnit(unit.getName());
					menuUnit.getContainer().add(unit.getContent());
					tab.getMenuUnits().add(menuUnit);
				}	
			}
		}
		
		// 排序菜单
		var menuService = EmsPlatform.getService().getMenuTabService();
		menuService.getSelectionModel().clearSelection();
		tabs.sort(ISortable.COMPARATOR);
		menuService.getTabs().addAll(tabs);
		menuService.selectTab(selectedTab);
		
		// 添加dock节点
		var dockService = EmsPlatform.getService().getDockService();
		dockables.sort(ISortable.COMPARATOR);
		dockService.getDockableNodes().addAll(dockables);
		
	}
	
}
