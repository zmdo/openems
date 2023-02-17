package com.schenchi.ems.platform.editor;

import com.schenchi.ems.platform.service.DataService;
import com.schenchi.ems.platform.utils.INodeContent;

import javafx.scene.Node;

public interface IEditor extends INodeContent {
	
	/**
	 * 编辑器唯一ID
	 * @return 字符串ID
	 */
	String getId();
	
	/**
	 * 返回编辑器面板内容
	 * @return 编辑器内容节点
	 */
	Node getContent();
	
	/**
	 * 获取当前状态
	 * @return
	 */
	EditorState getState();

	/**
	 * 创建
	 */
	void onCreate(DataService service);
	
	/**
	 * 开始
	 */
	void onStart(DataService service);
	
	/**
	 * 重新进入该编辑器
	 */
	void onResume(DataService service);
	
	/**
	 * 进入背景静止状态
	 */
	void onPause(DataService service);
	
	/**
	 * 结束
	 */
	void onStop(DataService service);
	
	/**
	 * 销毁
	 */
	void onDestroy(DataService service);
	
	/**
	 * 重新开始
	 * @param service
	 */
	void onRestart(DataService service);
}
