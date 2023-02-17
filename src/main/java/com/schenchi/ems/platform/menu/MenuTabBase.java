package com.schenchi.ems.platform.menu;

import com.tricolorfire.labfx.control.MenuUnit;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class MenuTabBase implements IMenuTab {

	private HBox box;
	private ObservableList<MenuUnit> menuUnits;
	
	public MenuTabBase() {
		this.box = new HBox();
		menuUnits = FXCollections.observableArrayList();
		menuUnits.addListener(new ListChangeListener<MenuUnit>() {
			@Override
			public void onChanged(Change<? extends MenuUnit> c) {
				while(c.next()) {
					// 添加列表
					var addedList = c.getAddedSubList();
					box.getChildren().addAll(addedList);
					// 移除列表
					var removedList = c.getRemoved();
					box.getChildren().removeAll(removedList);
				}
			}
		});
	}
	
	@Override
	public Node getContent() {
		return box;
	}

	@Override
	public ObservableList<MenuUnit> getMenuUnits() {
		return menuUnits;
	}

}
