package com.xyd.util;

import java.util.ResourceBundle;

public class ConfigUtil4Port {
	
	static ResourceBundle resource = ResourceBundle.getBundle("server");

	public static String get(String key) {
		return resource.getString(key);
	}
	
	public static int getPort() {
		String port = resource.getString("port");
		if(port == null)return 10000;
		return Integer.valueOf(port);
	}
}
