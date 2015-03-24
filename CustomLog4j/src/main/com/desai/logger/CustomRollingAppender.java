package com.desai.logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

public class CustomRollingAppender extends RollingFileAppender {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	Logger logger = LogManager.getLogger(CustomRollingAppender.class);

	private StringBuffer formattedString;

	//flag to determine if we already have formatted string ready
	// as we don't want to create a string every-time.
	private boolean isStringReady = false;

	// flag to determine if environment properties are converted to system
	// properties.
	private boolean isEnvPropsSet = false;

	@Override
	public void rollOver() {
		super.rollOver();

		if (!isStringReady) 
			prepareLogString();

		logger.info(formattedString);
	}
	
	private void prepareLogString(){
		formattedString = new StringBuffer();
		
		if (!isEnvPropsSet) {
			addEnvProps();
			isEnvPropsSet = true;
		}

		Properties properties = System.getProperties();
		Iterator<Object> i = properties.keySet().iterator();
		formattedString.append(LINE_SEPARATOR)
					   .append("/*********** System properties *************/")
					   .append(LINE_SEPARATOR);
		while (i.hasNext()) {
			Object key = i.next();
			formattedString.append("       ")
					.append(key)
					.append(" : ")
					.append(properties.get(key))
					.append(System.getProperty("line.separator"));
		}
		formattedString.append("/*********** System properties *************/")
		   			   .append(LINE_SEPARATOR);
		isStringReady = true;
	}
	
	private void addEnvProps(){
		for (Map.Entry<String, String> entry : System.getenv()
				.entrySet()) {
			System.setProperty(entry.getKey(), entry.getValue());
		}
	}
}
