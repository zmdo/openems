package com.schenchi.ems.views.dock;

import com.schenchi.ems.platform.dock.Dockable;
import com.schenchi.ems.platform.dock.IDockable;
import com.tricolorfire.labfx.dockFX.DockNode;
import com.tricolorfire.labfx.dockFX.DockPos;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TreeView;

@Dockable
public class LeftView implements IDockable {

	private DockNode content;
	
	public LeftView() {
		init();
	}
	
	private void init() {
		initContent();
	}

	private void initContent() {
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setMinWidth(300);
		
		TreeView<String> treeView = new TreeView<>();
		scrollPane.setContent(treeView);
		
		content = new DockNode(scrollPane, "检视器");
		content.setPrefSize(150, 100);
		
	}

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public String getId() {
		return "LeftView";
	}

	@Override
	public DockPos getPos() {
		return DockPos.LEFT;
	}

	@Override
	public DockNode getContent() {
		
		return content;
	}

}
