package com.schenchi.ems.views.task;

import java.util.Map;

import com.schenchi.ems.platform.EmsPlatform;
import com.schenchi.ems.platform.bus.INoticeListener;
import com.schenchi.ems.platform.task.ApplicationTask;
import com.schenchi.ems.platform.task.IApplicationTask;
import com.schenchi.ems.views.Global;
import com.schenchi.ems.views.NoticeEvent;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@ApplicationTask
public class initStatusBarTask implements IApplicationTask {

	private Label messageLabel;
	
	@Override
	public int getOrder() {
		return 70;
	}

	@Override
	public void before(Stage stage) {
		
		var statusBar = EmsPlatform.getService().getStatusBarService().getStatusBar();
		messageLabel = new Label("暂无消息");
		messageLabel.setPadding(new Insets(0, 0, 0, 5));
		statusBar.getContent().getChildren().add(messageLabel);
		
	}

	@Override
	public void after(Stage stage) {
		
		//添加事件监听
		Global.getNoticeBus().addListener(NoticeEvent.SWITCH_EDITOR, new INoticeListener() {
			
			@Override
			public void notice(Map<String, Object> params) {
				if(params.containsKey("name")) {
					String editorName = (String) params.get("name");
					String text = String.format("切换了编辑器[%s]",editorName);
					messageLabel.setText(text);	
				}
			}
			
		});
		
	}

}
