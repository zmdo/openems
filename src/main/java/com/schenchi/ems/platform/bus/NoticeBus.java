package com.schenchi.ems.platform.bus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class NoticeBus {
	
	private ObservableMap<String, ObservableList<INoticeListener>> notificationList;
	
	public NoticeBus() {
		notificationList = FXCollections.observableMap(new ConcurrentHashMap<>());
	}
	
	// 创建消息类型
	public void create(String messageType) {
		notificationList.put(messageType, FXCollections.observableArrayList());
	}
	
	// 获取所有监听器
	public ObservableList<INoticeListener> getListeners(String messageType) {
		return notificationList.get(messageType);
	}
	
	// 添加监听器
	public void addListener(String messageType,INoticeListener listener) {
		if(notificationList.containsKey(messageType)) {
			var list = notificationList.get(messageType);
			list.add(listener);
		}
	}
	
	// 通知
	public void notice(String messageType,Map<String,Object> params) {
		if(notificationList.containsKey(messageType)) {
			var list = notificationList.get(messageType);
			for (var listener : list) {
				listener.notice(copyParams(params));
			}
		}
	}
	
	private Map<String,Object> copyParams(Map<String,Object> params) {
		
		// 返回一个空参数集合
		if (params == null) {
			return new HashMap<String, Object>();
		}
		
		Map<String,Object> copy = new HashMap<>();
		var keySet = params.keySet();
		for (var key : keySet) {
			copy.put(key, params.get(key));
		}
		return copy;
	}
	
}
