package com.schenchi.ems.platform.bus;

import java.util.concurrent.ConcurrentHashMap;

import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class DataBus {

	@SuppressWarnings("rawtypes")
	private ObservableMap<String, Property> dataProperties;
	
	public DataBus () {
		dataProperties = FXCollections.observableMap(new ConcurrentHashMap<>());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListener(String dataname,ChangeListener listener) {
		var property = dataProperties.get(dataname);
		property.addListener(listener);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListener(String dataname, ListChangeListener listChangeListener) {
		var property = dataProperties.get(dataname);
		((ListProperty)property).addListener(listChangeListener);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addListener(String dataname, MapChangeListener mapChangeListener) {
		var property = dataProperties.get(dataname);
		((MapProperty)property).addListener(mapChangeListener);
	}
	
	@SuppressWarnings("rawtypes")
	public void put(String dataname,Property property) {
		dataProperties.put(dataname, property);
	}
	
	@SuppressWarnings("rawtypes")
	public void put(Property property) {
		dataProperties.put(property.getName(), property);
	}
	
	@SuppressWarnings("unchecked")
	public void change(String dataname,Object obj) {
		var property = dataProperties.get(dataname);
		property.setValue(obj);
	}
	
}
