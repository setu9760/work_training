package com.desai.logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

public class CustomRollingAppender extends RollingFileAppender {

	Logger logger = LogManager.getLogger(CustomRollingAppender.class);

	@Override
	public void rollOver() {
		super.rollOver();
		logger.info("file rolled over");
		StringBuffer buffer = new StringBuffer();
		Map<Object, Object> propertiesMap = new HashMap<Object, Object>();
		Properties properties = System.getProperties();
		Iterator<Object> i = properties.keySet().iterator();
		while (i.hasNext()) {
			Object key = i.next();
			logger.info(key + ":" + properties.get(key));
			propertiesMap.put(key, properties.get(key));
		}

	

	}
}
