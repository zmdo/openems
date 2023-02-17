package com.schenchi.ems.views.menu.tabunits.home;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.schenchi.ems.Main;
import com.schenchi.ems.platform.EmsPlatform;
import com.schenchi.ems.platform.editor.IEditor;
import com.schenchi.ems.platform.menu.IMenuFunctionalUnit;
import com.schenchi.ems.platform.menu.MenuFunctionalUnit;
import com.schenchi.ems.platform.service.EditorService;
import com.schenchi.ems.views.Global;
import com.schenchi.ems.views.NoticeEvent;
import com.tricolorfire.labfx.control.LabButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

@MenuFunctionalUnit
public class HomeRoutineUnit implements IMenuFunctionalUnit {
	
	private HBox content;
	
	private LabButton switchEditorButton;
	private LabButton simpleButton;
	
	public HomeRoutineUnit() {
		init();
	}
	
	private void init() {
		initContent();
		initButtons();
	}
	
	private void initContent() {
		content = new HBox();
	}
	
	private void initButtons() {
		initSwitchEditorButton();
		initSimpleButton();
	}


	private void initSwitchEditorButton() {
		Image image = null;
		try(var fin = Main.class.getResourceAsStream("/images/equipment/books_stack.png")) {
			image = new Image(fin);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
		switchEditorButton = new LabButton(image,"切换\n编辑器");
		switchEditorButton.setMaxWidth(80);
		// 添加监听器
		switchEditorButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 执行切换任务
				EditorService editorService = EmsPlatform.getService().getEditorService();
				int total = editorService.getEidtors().size();
				int index = editorService.getAwakenEditorModel().getSelectedIndex();
				int next = (index + 1)%total;
				editorService.getAwakenEditorModel().select(next);
				
				// 通知事件总线
				Map<String, Object> parmas = new HashedMap<>();
				IEditor editor = editorService.getEidtors().get(next);
				parmas.put("name", editor.getId());
				Global.getNoticeBus().notice(NoticeEvent.SWITCH_EDITOR, parmas);
			}
		});
		content.getChildren().add(switchEditorButton);
	}

	private void initSimpleButton() {
		Image image = null;
		try(var fin = Main.class.getResourceAsStream("/images/equipment/edit_button.png")) {
			image = new Image(fin);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
		simpleButton = new LabButton(image,"简单按钮");
		content.getChildren().add(simpleButton);
	}

	@Override
	public Node getContent() {
		return content;
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public String getName() {
		return "常规";
	}

	@Override
	public String getMenuTabName() {
		return "主页";
	}

}
