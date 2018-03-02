package org.william.apps.utility_apps;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public enum LogUtil {
	INST;
	
	private final Map<String, Logger> LOGS;

	private LogUtil() {
		LOGS = new HashMap<>();
	}
	
	public void log(String name) {
		
	}
}
