package com.schenchi.ems.platform.menu;

public class DefaultMenuTab extends MenuTabBase {
	
	private String name;
	private int order;
	private boolean temporary;
	private boolean defaultSelected;
	
	public DefaultMenuTab() {
		this("",0,false,false);
	}
	
	public DefaultMenuTab(String name) {
		this(name,0,false,false);
	}
	
	public DefaultMenuTab(String name,int order,boolean defaultSelected,boolean temporary) {
		this.name = name;
		this.order = order;
		this.temporary = temporary;
		this.defaultSelected = defaultSelected;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public boolean isTemporary() {
		return temporary;
	}

	@Override
	public boolean isDefaultSelected() {
		return defaultSelected;
	}

}
