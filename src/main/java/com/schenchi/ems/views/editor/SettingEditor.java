package com.schenchi.ems.views.editor;

import com.schenchi.ems.platform.editor.Editor;
import com.schenchi.ems.platform.editor.EditorBase;

import javafx.scene.Node;
import javafx.scene.control.Label;

@Editor
public class SettingEditor extends EditorBase {

	@Override
	public String getId() {
		return "SettingEditor";
	}

	@Override
	public Node getContent() {
		return new Label("这是Setting编辑器");
	}

}
