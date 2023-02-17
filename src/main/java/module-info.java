module com.schenchi.ems {
	
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
	requires transitive com.tricolorfire.labfx.controls;
	requires javafx.graphics;
	requires com.jfoenix;
	requires javafx.base;
	requires org.yaml.snakeyaml;
	requires httpclient;
	requires httpmime;
	requires httpcore;
	requires fastjson;
	requires com.fasterxml.jackson.core;
	requires java.sql;
	
    requires org.apache.poi.poi;
    requires org.apache.xmlbeans;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.commons.collections4;
    requires org.apache.commons.codec;
    requires org.apache.commons.compress;
    requires org.apache.commons.io;
	requires org.apache.poi.ooxml;
	requires com.google.zxing;
	requires com.google.zxing.javase;
    
	opens com.schenchi.ems;
	opens com.schenchi.ems.platform.application;
    opens com.schenchi.ems.platform.dock;
    opens com.schenchi.ems.platform;
    
    opens com.schenchi.ems.views.dock;
    opens com.schenchi.ems.views.dialog;
    opens com.schenchi.ems.views.menu.tabs;
    opens com.schenchi.ems.views.menu.tabunits.setting;
    opens com.schenchi.ems.views.task;
    
    exports com.schenchi.ems;
}
