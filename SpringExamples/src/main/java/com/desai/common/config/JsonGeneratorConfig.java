package main.java.com.desai.common.config;

import main.java.com.desai.common.initial.IOutputGenerator;
import main.java.com.desai.common.initial.JsonOutputGenerator;

import org.springframework.context.annotation.Bean;

public class JsonGeneratorConfig {

	@Bean(name = "JsonOutputGeneratorBean")
	public IOutputGenerator jsonOutputGenerator() {
		return new JsonOutputGenerator();
	}

}
