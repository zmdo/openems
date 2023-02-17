package com.schenchi.ems.platform.frame.statusbar;

import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class StatusBar extends BorderPane {
	
	private HBox content;
	
	public StatusBar() {
		init();
	}
	
	private void init() {
		content = new HBox();
		content.setMinHeight(20);
		
		setTop(new Separator());
		setCenter(content);
		
	}
	
	public HBox getContent() {
		return content;
	}
}
