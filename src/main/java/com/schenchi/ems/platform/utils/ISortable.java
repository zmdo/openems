package com.schenchi.ems.platform.utils;

import java.util.Comparator;

public interface ISortable {
	
	public static final Comparator<ISortable> COMPARATOR = new Comparator<ISortable>() {
		
		@Override
		public int compare(ISortable o1, ISortable o2) {
			return o1.getOrder() - o2.getOrder();
		}
		
	};
	
	int getOrder();
	
}
