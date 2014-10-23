package com.desai.common.config;

import org.springframework.context.annotation.Bean;

import com.desai.common.initial.CsvOutputGenerator;
import com.desai.common.initial.IOutputGenerator;

public class CsvGeneratorConfig {

	@Bean(name = "CsvoutputGeneratorbean")
	public IOutputGenerator csvOutputGenerator() {
		return new CsvOutputGenerator();
	}

}
