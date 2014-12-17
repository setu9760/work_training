package com.desai.logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.AbstractLookup;
import org.apache.logging.log4j.core.lookup.StrLookup;

@Plugin(name = "property", category = StrLookup.CATEGORY)
public class CustomLookup extends AbstractLookup {

	private static AtomicLong aLong = new AtomicLong(0);

	@Override
	public String lookup(LogEvent event, String key) {
		return getCustomHeader(key);
	}

	private static String getCustomHeader(final String key) {

		if (aLong.getAndIncrement() == 0) {
			return "";
		}
		if (key.equalsIgnoreCase("customKey")) {
			StringBuilder builder = new StringBuilder();
			for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
				System.setProperty(entry.getKey(), entry.getValue());
			}

			Properties properties = System.getProperties();
			Iterator<Object> i = properties.keySet().iterator();
			builder.append("/*********** Logging system properties *************/");
			while (i.hasNext()) {
				Object key1 = i.next();
				builder.append("## ")
					.append(key1.toString())
					.append(" : ")
					.append(properties.get(key1));
				builder.append(System.getProperty("line.separator"));
			}
			builder.append("/******** End of system properties *********/");
			return builder.toString();
		} else {
			return "no CustomKey";
		}
	}
}
