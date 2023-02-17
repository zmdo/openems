package com.schenchi.ems.platform.service;


import com.schenchi.ems.platform.editor.IEditor;
import com.schenchi.ems.platform.frame.EditorView;

import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

public class EditorService {
	
	private EditorView editorView;
	
	public EditorService() {
		this(new EditorView());
	}
	
	public EditorService(EditorView editorView) {
		this.editorView = editorView;
	}
	
	public boolean removeEditor(String id) {
		return editorView.restartEditor(id);
	}
	
	public IEditor getEditor(String id) {
		return editorView.getEditor(id);
	}
	
	public boolean stopEditor(String id) {
		return editorView.stopEditor(id);
	}
	
	public boolean restartEditor(String id) {
		return editorView.restartEditor(id);
	}
	
	public DataService getEditorDataService(String id) {
		return editorView.getEditorDataService(id);
	}
	
	public ObservableList<IEditor> getEidtors() {
		return editorView.getEditors();
	}
	
	public SingleSelectionModel<IEditor> getAwakenEditorModel() {
		return editorView.getAwakenEditorModel();
	}
	
	public EditorView getEditorView() {
		return editorView;
	}
}
