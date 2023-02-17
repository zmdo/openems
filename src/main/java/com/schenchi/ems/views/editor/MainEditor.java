package com.schenchi.ems.views.editor;

import com.schenchi.ems.platform.editor.Editor;
import com.schenchi.ems.platform.editor.EditorBase;

import javafx.scene.Node;
import javafx.scene.control.Label;

@Editor
public class MainEditor extends EditorBase {

	@Override
	public String getId() {
		return "MainEditor";
	}

	@Override
	public Node getContent() {
		return new Label("这是Main编辑器");
	}

}
