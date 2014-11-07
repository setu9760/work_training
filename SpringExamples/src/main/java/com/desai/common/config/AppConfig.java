package com.desai.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.desai.common.config.CsvGeneratorConfig;

@Configuration
// @Import({ CsvGeneratorConfig.class, JsonGeneratorConfig.class })
// @Import({ NumberConfig.class })
public class AppConfig {
	CsvGeneratorConfig config;
}
