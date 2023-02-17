package com.schenchi.ems.platform.frame;

import java.util.HashMap;
import java.util.Map;

import com.schenchi.ems.platform.editor.IEditor;
import com.schenchi.ems.platform.service.DataService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.BorderPane;

public class EditorView extends BorderPane {
	
	private Map<String,DataService> editorDataServiceMap;
	private Map<String,IEditor> editorMap;
	
	
	// 所有的编辑器
	private ObservableList<IEditor> editors;
	
	// 可用的编辑器
	private ObservableList<IEditor> enableEditors;
	// 停止的编辑器
	private ObservableList<IEditor> stoppedEditors;
	
	private SingleSelectionModel<IEditor> awakenEditorModel;
	
	public EditorView() {
		init();
	}
	
	private void init() {
		
		// 初始化编辑器映射
		initEditorMap();
		
		// 初始化编辑器面板列表及设置监听器
		initEditors();
		initEnableEditors();
		initStoppedEditors();
		
		// 初始化唤醒编辑器模型
		initAwakenEditorModel();
		
	}

	private void initEditorMap() {
		editorDataServiceMap = new HashMap<>();
		editorMap = new HashMap<>();
	}

	private void initAwakenEditorModel() {
		
		awakenEditorModel = new SingleSelectionModel<IEditor>() {
			@Override
			protected IEditor getModelItem(int index) {
				return enableEditors.get(index);
			}

			@Override
			protected int getItemCount() {
				return enableEditors.size();
			}
		};
		
		awakenEditorModel.selectedItemProperty().addListener(new ChangeListener<IEditor>() {
			@Override
			public void changed(
					ObservableValue<? extends IEditor> observable, 
					IEditor oldEditor, 
					IEditor newEditor) {
							
				if (oldEditor != null) {
					// 获取Service
					var sercive= editorDataServiceMap.get(oldEditor.getId());
					// 暂停原来的编辑器
					oldEditor.onPause(sercive);	
				}
				
				if (newEditor != null) {
					// 获取Service
					var sercive= editorDataServiceMap.get(newEditor.getId());
					// 继续被唤醒的编辑器
					newEditor.onResume(sercive);	
				}
				
				// 展示被唤醒的编辑器
				setCenter(newEditor.getContent());
			}
		});
		
	}

	private void initEditors() {
		editors = FXCollections.observableArrayList();
		editors.addListener(new ListChangeListener<IEditor>(){
			
			@Override
			public void onChanged(Change<? extends IEditor> c) {
				while(c.next()) {
					var addedList = c.getList();
					for (var editor : addedList) {
						String id = editor.getId();
						if (!editorMap.containsKey(id)) {
							
							// 创建一个数据服务
							DataService service = new DataService();
							
							// 调用 editor 的创建方法
							editor.onCreate(service);
							
							// 直接加入编辑器
							enableEditors.add(editor);
							editorMap.put(id, editor);
							editorDataServiceMap.put(id, service);
							
						}
					}
					
					var removedList = c.getRemoved();
					for (var editor : removedList) {
						String id = editor.getId();
						if (editorMap.containsKey(id)) {
							
							// 判断是否停止
							if (!stoppedEditors.contains(editor)) {
								// 如果没有停止就让它停止
								enableEditors.remove(editor);
							}
							
							// 调用 editor 的销毁方法
							editor.onDestroy(editorDataServiceMap.get(id));
							
							// 彻底移除编辑器
							stoppedEditors.remove(editor);
							editorDataServiceMap.remove(id);
							editorMap.remove(id);
						}
					}
				}
			}
			
		});
	}
	
	private void initStoppedEditors() {
		stoppedEditors = FXCollections.observableArrayList();
		stoppedEditors.addListener(new ListChangeListener<IEditor>() {
			@Override
			public void onChanged(Change<? extends IEditor> c) {
				while(c.next()) {
					
					// 让所有加入的编辑器调用停止方法
					var addedList = c.getList();
					for (var editor : addedList) {
						var sercive = editorDataServiceMap.get(editor.getId());
						// 调用停止方法
						editor.onStop(sercive);
					}
					
				}
			}
		});
	}

	private void initEnableEditors() {
		enableEditors = FXCollections.observableArrayList();
		enableEditors.addListener(new ListChangeListener<IEditor>() {

			@Override
			public void onChanged(Change<? extends IEditor> c) {
				while(c.next()) {
					
					// 加入该列表就会自动启动编辑器
					var addedList = c.getList();
					for (var editor : addedList) {
						// 获取服务
						var sercive = editorDataServiceMap.get(editor.getId());
						// 调用启动方法
						editor.onStart(sercive);
					}
					
					// 将移除的编辑器加入停止列表中
					var removedList = c.getRemoved();
					for (var editor : removedList) {
						stoppedEditors.add(editor);
					}
				}
			}
			
		});
	}
	
	/**
	 * 获取编辑器
	 * @return
	 */
	public ObservableList<IEditor> getEditors(){
		return editors;
	}
	
	public IEditor getEditor(String id) {
		return editorMap.get(id);
	}
	
	public boolean stopEditor(String id) {
		
		if (editorMap.containsKey(id)) {
			// 检查可用编辑器是否包含所需要停止的编辑器
			var editor = editorMap.get(id);
			if(enableEditors.contains(editor)) {
				// 在可用编辑器中直接移除
				enableEditors.remove(editor);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean restartEditor(String id) {
		if (editorMap.containsKey(id)) {
			// 检查已经停止的编辑器是否包含需要重启的编辑器
			var editor = editorMap.get(id);
			if (stoppedEditors.contains(editor)) {
				
				// 获取服务
				var service = editorDataServiceMap.get(editor.getId());
				
				// 重启编辑器
				stoppedEditors.remove(editor);
				editor.onRestart(service);
				enableEditors.add(editor);
				return true;
				
			}
		}
		return false;
	}
	
	public DataService getEditorDataService(String id) {
		return editorDataServiceMap.get(id);
	}
	
	public SingleSelectionModel<IEditor> getAwakenEditorModel() {
		return awakenEditorModel;
	}

}
