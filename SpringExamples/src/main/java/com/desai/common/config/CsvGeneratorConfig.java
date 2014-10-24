package com.desai.common.config;

import com.desai.common.initial.CsvOutputGenerator;
import com.desai.common.initial.IOutputGenerator;

import org.springframework.context.annotation.Bean;

public class CsvGeneratorConfig {

	@Bean(name = "CsvoutputGeneratorbean")
	public IOutputGenerator csvOutputGenerator() {
		return new CsvOutputGenerator();
	}

}
