package com.schenchi.ems.platform;

public class EmsPlatform {
	
	private static final Service SERVICE = Service.INSTANCE;
	
	public static Service getService() {
		return SERVICE;
	}
	
}
