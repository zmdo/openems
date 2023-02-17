package com.schenchi.ems.views.menu.tabs;

import com.schenchi.ems.platform.menu.MenuTab;
import com.schenchi.ems.platform.menu.MenuTabBase;

@MenuTab
public class SettingTab extends MenuTabBase {
	
	@Override
	public String getName() {
		return "设置";
	}
	
	@Override
	public int getOrder() {
		return 30;
	}
	
}
