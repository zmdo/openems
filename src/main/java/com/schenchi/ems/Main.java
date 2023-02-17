package com.schenchi.ems;

import com.schenchi.ems.platform.application.ApplicaitonInitializer;
import com.schenchi.ems.platform.application.ApplicaitonLauncher;

@ApplicaitonLauncher("/config/application.yml")
public class Main {
    
	public static void main(String[] args) {
		ApplicaitonInitializer.start(args);
	}
	
}