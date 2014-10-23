package com.desai.common.config;

import org.springframework.context.annotation.Bean;

import com.desai.common.CsvOutputGenerator;
import com.desai.common.IOutputGenerator;

public class CsvGeneratorConfig {

	@Bean(name = "CsvoutputGeneratorbean")
	public IOutputGenerator csvOutputGenerator() {
		return new CsvOutputGenerator();
	}

}
