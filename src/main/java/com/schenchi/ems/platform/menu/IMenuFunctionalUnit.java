package com.schenchi.ems.platform.menu;

import com.schenchi.ems.platform.utils.INodeContent;
import com.schenchi.ems.platform.utils.ISortable;

public interface IMenuFunctionalUnit extends INodeContent,ISortable {
	
	/**
	 * 单元ID
	 * @return
	 */
	default String getId() {
		return getMenuTabName() + "." + getName();
	}
	
	/**
	 * 单元名
	 * @return
	 */
	String getName();
	
	/**
	 * 获取所在的单元选项卡名
	 * @return
	 */
	String getMenuTabName();
	
}
