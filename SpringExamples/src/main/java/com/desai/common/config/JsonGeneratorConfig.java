package com.desai.common.config;

import org.springframework.context.annotation.Bean;

import com.desai.common.IOutputGenerator;
import com.desai.common.JsonOutputGenerator;

public class JsonGeneratorConfig {

	@Bean(name = "JsonOutputGeneratorBean")
	public IOutputGenerator jsonOutputGenerator() {
		return new JsonOutputGenerator();
	}

}
