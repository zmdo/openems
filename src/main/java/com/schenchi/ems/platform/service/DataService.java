package com.schenchi.ems.platform.service;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class DataService implements Map<String, Object> {
	
	private Map<String,Object> data;
	
	public DataService() {
		data = new Hashtable<>();
	}
	
	/*-----------------*
	 |    数据的添加方式    |
	 *-----------------*/
	
	@Override
	public Object put(String key,Object obj) {
		return data.put(key, obj);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		data.putAll(m);
	}
	
	/*-----------------*
	 |    数据的获得方式    |
	 *-----------------*/
	
	public <T> T get(String key,Class<T> t) {
		return t.cast(data.get(key));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T)data.get(key);
	}
	
	@Override
	public Object get(Object key) {
		return data.get(key);
	}
	
	public Map<String,Object> getAll() {
		return data;
	}
	
	public Byte getByte(String key) {
		return get(key,Byte.class);
	}
	
	public Short getShort(String key) {
		return get(key,Short.class);
	}
	
	public Long getLong(String key) {
		return get(key,Long.class);
	}
	
	public Integer getInteger(String key) {
		return get(key,Integer.class);
	}
	
	public Double getDouble(String key) {
		return get(key,Double.class);
	}
	
	public Boolean getBoolean(String key) {
		return get(key,Boolean.class);
	}
	
	public Character getCharacter(String key) {
		return get(key,Character.class);
	}
	
	public String getString(String key) {
		return get(key,String.class);
	}
	
	/*-----------------*
	 |    数据的移除方式    |
	 *-----------------*/
	
	@Override
	public Object remove(Object key) {
		return data.remove(key);
	}

	@Override
	public void clear() {
		data.clear();
	}
	
	/*-----------------*
	 |        其他      |
	 *-----------------*/
	
	@Override
	public boolean containsKey(Object key) {
		return data.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value) {
		return data.containsValue(value);
	}
	
	@Override
	public Set<String> keySet() {
		return data.keySet();
	}
	
	@Override
	public Set<Entry<String, Object>> entrySet() {
		return data.entrySet();
	}
	
	@Override
	public Collection<Object> values() {
		return data.values();
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
}
