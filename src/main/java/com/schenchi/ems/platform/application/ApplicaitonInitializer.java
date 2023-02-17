package com.schenchi.ems.platform.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;

import com.schenchi.ems.platform.task.IApplicationTask;

import javafx.application.Application;

/**
 * 应用初始化器
 * 所有的初始化都会从此加载
 */
public class ApplicaitonInitializer {
	
	public static final String DEFAULT_APPLICATION_CONFIGURE_PATH = "/application.yml";
	
	private ApplicaitonInitializer() {}

	// 加载启动项
	public static ApplicationConfigure loadConfigure(String configurePath) {
		var yaml = new Yaml();
		// 加载配置文件
		ApplicationConfigure config = null;
		try(var fin = new FileInputStream(configurePath)) {
			config = yaml.loadAs(fin, ApplicationConfigure.class);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	private static ApplicationConfigure loadConfigure(Class<?> clazz, String configurePath) {
       	
		// 获取真实路径
    	var realPath = clazz.getResource(configurePath).getFile();
    	
    	// 获取配置
		var config = loadConfigure(realPath);
		
    	// 重定向logo路径
    	String logoPath = config.getApplicationLogo();
    	logoPath = new File(clazz.getResource(logoPath).getFile()).getPath();
    	config.setApplicationLogo(logoPath);
    	
		return config;
	}
	
	public static void start(ApplicationConfigure config,String...args) {

		// 设置配置
		MainApplication.setConfigure(config);
		
		// 启动
		Application.launch(MainApplication.class,args);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void start(String...args) {
		// 读取调用栈中调用该方法的类
		
        // Figure out the right class to call
        StackTraceElement[] cause = Thread.currentThread().getStackTrace();

        boolean foundThisMethod = false;
        String callingClassName = null;
        for (StackTraceElement se : cause) {
            // Skip entries until we get to the entry for this class
            String className = se.getClassName();
            String methodName = se.getMethodName();
            
            if (foundThisMethod) {
                callingClassName = className;
                break;
            } else if (ApplicaitonInitializer.class.getName().equals(className)
                    && "start".equals(methodName)) {

                foundThisMethod = true;
            }
            
        }

        if (callingClassName == null) {
            throw new RuntimeException("Error: unable to determine Application class");
        }

        try {
            Class theClass = Class.forName(callingClassName, false,
                               Thread.currentThread().getContextClassLoader());
            
            // 查找调用方法的类的注解
            var anno = theClass.getDeclaredAnnotation(ApplicaitonLauncher.class);
            if (anno != null) {
            	
            	// 获取配置路径
            	String configPath = ((ApplicaitonLauncher)anno).value();
            	
            	// 如果为空那么设置默认的路径
            	if(configPath.equals("")) {
            		configPath = DEFAULT_APPLICATION_CONFIGURE_PATH;
            	}
            	
            	// 获取真实路径
            	var config = loadConfigure(theClass,configPath);
            	start(config,args);
            	
            } else {
            	throw new RuntimeException("Error: " + theClass
                        + "invalid annotation :"  + ApplicaitonLauncher.class);
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
	}
	
	/**
	 * 添加一个任务
	 * @param task
	 */
	public static void addTask(IApplicationTask task) {
		MainApplication.addTask(task);
	}
	
}
