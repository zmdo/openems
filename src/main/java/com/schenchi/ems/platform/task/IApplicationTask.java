package com.schenchi.ems.platform.task;

import com.schenchi.ems.platform.utils.ISortable;

import javafx.stage.Stage;

public interface IApplicationTask extends ISortable {
	
	public void before(Stage stage);
	
	public void after(Stage stage);
	
}
