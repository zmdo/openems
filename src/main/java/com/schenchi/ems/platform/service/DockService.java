package com.schenchi.ems.platform.service;

import com.schenchi.ems.platform.dock.IDockable;
import com.tricolorfire.labfx.dockFX.DockNode;
import com.tricolorfire.labfx.dockFX.DockPane;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class DockService {
	
	private DockPane dockPane;
	
	private ObservableList<IDockable> dockableNodes;
	
	public DockService() {
		
		dockPane = new DockPane();

		dockableNodes = FXCollections.observableArrayList();
		dockableNodes.addListener(new ListChangeListener<IDockable>() {
			@Override
			public void onChanged(Change<? extends IDockable> c) {
				while(c.next()) {
					
					var addedList = c.getAddedSubList();
					for (var dockable : addedList) {
					 	DockNode node = dockable.getContent();
					 	node.dock(dockPane, dockable.getPos());
					}
					
				}
			}
		});
	}
	
	public DockPane getDockPane() {
		return dockPane;
	}

	public ObservableList<IDockable> getDockableNodes() {
		return dockableNodes;
	}
	
	public IDockable getDockableNodeById(String id) {
		for (var node : dockableNodes) {
			if(node.getId().equals(id)) {
				return node;
			}
		}
		return null;
	}
	
}
