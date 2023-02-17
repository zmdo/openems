package com.schenchi.ems.platform.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageScanner {
	
	private ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	private ClassScanHandler handler;

	public PackageScanner() {
		this(null);
	}
	
	public PackageScanner(ClassLoader classLoader,ClassScanHandler handler) {
		this.classLoader = classLoader;
		this.handler = handler;
	}
	
	public PackageScanner(ClassScanHandler handler) {
		this.handler = handler;
	}
	
	protected void handle(Class<?> clazz) {
		if (handler != null) {
			handler.handle(clazz);
		}
	}
	
	public void scan(String packagePath) {
		scan(packagePath,classLoader);
	}
	
	public void scan(String packagePath,ClassLoader classLoader) {
		
    	URL url = classLoader.getResource(packagePath.replace(".", "/"));
		String protocol = url.getProtocol();  
        if ("file".equals(protocol)) {  
            // 本地自己可见的代码  
       	    findClassLocal(packagePath,classLoader);
        } else if ("jar".equals(protocol)) {  
            // 引用jar包的代码  
            findClassJar(packagePath,classLoader);  
        }
        
	}
	
	private void findClassLocal(String packName,ClassLoader classLoader) {
		
		URI url = null ;
		try {
			url = classLoader.getResource(packName.replace(".", "/")).toURI();
		} catch (URISyntaxException e1) {
			throw new RuntimeException("未找到策略资源");
		}
		
		File file = new File(url);
		file.listFiles(new FileFilter() {
			public boolean accept(File chiFile) {
				
				if(chiFile.isDirectory()) {
					findClassLocal(packName+"."+chiFile.getName(),classLoader);
				}
				
				if(chiFile.getName().endsWith(".class")){
					
					Class<?> clazz = null;
					try {
						clazz = classLoader.loadClass(packName + "." + chiFile.getName().replace(".class", ""));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					handle(clazz);
					
					return true;
				}
				return false;
			}
			
		});
		
	}

	private void findClassJar(String packName,ClassLoader classLoader) {
		
		String pathName = packName.replace(".", "/");
		JarFile jarFile  = null;
		try {
			URL url = classLoader.getResource(pathName);
			JarURLConnection jarURLConnection  = (JarURLConnection )url.openConnection();
			jarFile = jarURLConnection.getJarFile();  
		} catch (IOException e) {
			throw new RuntimeException("未找到策略资源");
		}
		
		Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
        	
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName();
            
            if(jarEntryName.contains(pathName) && !jarEntryName.equals(pathName+"/")){
            	
            	if(jarEntry.getName().endsWith(".class")){
            		
    				Class<?> clazz = null;
    				try {
    					clazz = classLoader.loadClass(jarEntry.getName().replace("/", ".").replace(".class", ""));
    				} catch (ClassNotFoundException e) {
    					e.printStackTrace();
    				}
    				
    				// 如果没有重复扫描
    				handle(clazz);
            	}
            }
            
        }
	}
	
	public ClassScanHandler getHandler() {
		return handler;
	}

	public void setHandler(ClassScanHandler handler) {
		this.handler = handler;
	}
	
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}
