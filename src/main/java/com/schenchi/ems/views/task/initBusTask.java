package com.schenchi.ems.views.task;

import com.schenchi.ems.platform.task.ApplicationTask;
import com.schenchi.ems.platform.task.IApplicationTask;
import com.schenchi.ems.views.Global;
import com.schenchi.ems.views.NoticeEvent;

import javafx.stage.Stage;

@ApplicationTask
public class initBusTask implements IApplicationTask {

	@Override
	public int getOrder() {
		return 10;
	}

	@Override
	public void before(Stage stage) {
		// 创建消息类型
		Global.getNoticeBus().create(NoticeEvent.SWITCH_EDITOR);
	}

	@Override
	public void after(Stage stage) {

	}
}
