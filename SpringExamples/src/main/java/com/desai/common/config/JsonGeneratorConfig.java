package com.desai.common.config;

import org.springframework.context.annotation.Bean;

import com.desai.common.initial.IOutputGenerator;
import com.desai.common.initial.JsonOutputGenerator;

public class JsonGeneratorConfig {

	@Bean(name = "JsonOutputGeneratorBean")
	public IOutputGenerator jsonOutputGenerator() {
		return new JsonOutputGenerator();
	}

}
