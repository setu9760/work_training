package com.desai.common.config;

import org.springframework.context.annotation.Bean;

import com.desai.common.JsonOutputGenerator;
import com.desai.common.interfaces.IOutputGenerator;

public class JsonGeneratorConfig {

	@Bean(name = "JsonOutputGeneratorBean")
	public IOutputGenerator jsonOutputGenerator() {
		return new JsonOutputGenerator();
	}

}
