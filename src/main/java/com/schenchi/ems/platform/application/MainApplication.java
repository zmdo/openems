package com.schenchi.ems.platform.application;

import com.schenchi.ems.platform.service.MenuTabService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.schenchi.ems.platform.EmsPlatform;
import com.schenchi.ems.platform.frame.MainView;
import com.schenchi.ems.platform.task.IApplicationTask;
import com.schenchi.ems.platform.utils.ISortable;
import com.tricolorfire.labfx.dockFX.DockNode;
import com.tricolorfire.labfx.dockFX.DockPos;

public class MainApplication extends Application {

    // 界面最小宽度和高度
    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 480;
    
    private static ApplicationConfigure configure;
    private static List<IApplicationTask> tasks = new ArrayList<>();

    // 设置配置
    public static void setConfigure(ApplicationConfigure configure) {
    	MainApplication.configure = configure;
    }
    
    // 设置任务
    public static void addTask(IApplicationTask task) {
    	tasks.add(task);
    }
    
    @Override
    public void start(Stage stage) throws IOException {

    	// 首先将编辑器置于可停靠面板内
    	var editorView = EmsPlatform.getService().getEditorService().getEditorView();
    	var dockPane = EmsPlatform.getService().getDockService().getDockPane();
    	
    	DockNode editorNode = new DockNode(editorView);
    	editorNode.setDockTitleBar(null);
    	editorNode.setPrefSize(
    			350, 
    			100);
    	editorNode.dock(dockPane, DockPos.CENTER);
    	
    	// 创建内部任务
    	var innerTask = new InnerScanTask(configure.getApplicationViewsPackage());
    	
    	// 扫描应用任务
    	innerTask.before(stage);
    	
    	// 排序运行任务
    	tasks.sort(ISortable.COMPARATOR);
    	// 执行任务
		for (var task : tasks) {
			task.before(stage);
		}
    	
    	// 然后执行扫描任务
		innerTask.after(stage);
        // 执行任务
		for (var task : tasks) {
			task.after(stage);
		}
		
    	// 主界面
    	var mainView = new MainView();
    	Scene scene = new Scene(
    			mainView, 
    			configure.getWindowWidth(), 
    			configure.getWindowHeight());
    	
        // 设置最小宽度和高度
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
       
        // 设置标题
        stage.setTitle(configure.getApplicationName());
        // 设置全屏
        stage.setFullScreen(configure.isFullScreen());
        // 设置图标
        stage.getIcons().add(new Image(configure.getApplicationLogo()));
        
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        dockPane.initializeDefaultUserAgentStylesheet();
    }
    
}