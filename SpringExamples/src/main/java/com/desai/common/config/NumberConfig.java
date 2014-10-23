package com.desai.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.desai.common.singleton.Number;

public class NumberConfig {

	@Bean(name = "numberBean")
	@Scope("prototype")
	public Number getNumber() {
		return new Number(15);
	}

}
