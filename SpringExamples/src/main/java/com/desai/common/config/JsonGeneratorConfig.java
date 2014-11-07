package com.desai.common.config;

import com.desai.common.initial.IOutputGenerator;
import com.desai.common.initial.JsonOutputGenerator;

import org.springframework.context.annotation.Bean;

public class JsonGeneratorConfig {

	@Bean(name = "JsonOutputGeneratorBean")
	public IOutputGenerator jsonOutputGenerator() {
		return new JsonOutputGenerator();
	}

}
