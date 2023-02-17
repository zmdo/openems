package com.schenchi.ems.views;

import com.schenchi.ems.platform.bus.DataBus;
import com.schenchi.ems.platform.bus.NoticeBus;

public class Global {
	
	private static final DataBus dataBus = new DataBus();
	private static final NoticeBus noticeBus = new NoticeBus();
	
	public static DataBus getDataBus() {
		return dataBus;
	}
	
	public static NoticeBus getNoticeBus() {
		return noticeBus;
	}
	
}
