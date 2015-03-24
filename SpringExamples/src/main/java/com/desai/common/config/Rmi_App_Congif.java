package com.desai.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.desai.common.rmi.Calculation;
import com.desai.common.rmi.CalculationImpl;

@Configuration
public class Rmi_App_Congif {

	// @Bean(name = "caclulationImplBean")
	public Calculation getCalculation() {
		return new CalculationImpl();
	}

	@Bean
	public RmiServiceExporter getRmiServiceExporter() {
		RmiServiceExporter rmi = new RmiServiceExporter();
		rmi.setService(getCalculation());
		rmi.setServiceInterface(Calculation.class);
		rmi.setServiceName("Calculation");
		rmi.setReplaceExistingBinding(true);
		rmi.setRegistryPort(9595);
		return rmi;
	}
}
