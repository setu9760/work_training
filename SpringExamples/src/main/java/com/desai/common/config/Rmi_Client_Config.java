package com.desai.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.desai.common.rmi.Calculation;

@Configuration
public class Rmi_Client_Config {

	@Value("rmi://localhost:1099/calculationServise")
	String SERVICE_URL;

	@Bean(name = "caclulationBean")
	public RmiProxyFactoryBean getRmiProxyFactoryBean() {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceUrl(SERVICE_URL);
		rmiProxy.setServiceInterface(Calculation.class);
		return rmiProxy;
	}

}
