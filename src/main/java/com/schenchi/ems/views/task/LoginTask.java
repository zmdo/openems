package com.schenchi.ems.views.task;

import com.schenchi.ems.platform.task.ApplicationTask;
import com.schenchi.ems.platform.task.IApplicationTask;
import com.schenchi.ems.views.dialog.LoginDialog;

import javafx.stage.Stage;

@ApplicationTask
public class LoginTask implements IApplicationTask {

	@Override
	public int getOrder() {
		return 2;
	}

	@Override
	public void before(Stage stage) {
		
		LoginDialog loginDialog = new LoginDialog();
		var result = loginDialog.showAndWait();
		
		 // 验证
		if (result.get() == null) {
			// 直接推出
			System.exit(0);
		}
		
	}

	@Override
	public void after(Stage stage) {}
	
}
