package com.desai.java;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.desai.logger.CustomRollingAppender;

public class main_method {

	// static Logger logger = LogManager.getLogger(main_method.class);

	static org.apache.logging.log4j.Logger logger2 = org.apache.logging.log4j.LogManager
			.getLogger(main_method.class);

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			logger2.info("main method");
		}

	}
}
