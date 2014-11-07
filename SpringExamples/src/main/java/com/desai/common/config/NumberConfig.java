package com.desai.common.config;

import com.desai.common.singleton.Number;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class NumberConfig {

	@Bean(name = "numberBean")
	@Scope("prototype")
	public Number number() {
		return new Number(10);
	}

}
