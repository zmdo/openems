package com.schenchi.ems.platform.menu;

import com.schenchi.ems.platform.utils.INodeContent;
import com.schenchi.ems.platform.utils.ISortable;
import com.tricolorfire.labfx.control.MenuUnit;

import javafx.collections.ObservableList;

public interface IMenuTab extends INodeContent,ISortable {
	
	/**
	 * 获得菜单单元容器
	 * @return
	 */
	ObservableList<MenuUnit> getMenuUnits();
	
	/**
	 * 菜单名
	 * @return
	 */
	default String getName() {
		return "";
	}
	
	/**
	 * 是否是临时选项卡
	 * @return
	 */
	default boolean isTemporary() {
		return false;
	}
	
	/**
	 * 是否为默认选中项
	 * @return
	 */
	default boolean isDefaultSelected() {
		return false;
	}
	
}
