package spring.desai.mains;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import spring.desai.config.Config;
import spring.desai.dao.SubjectDao;

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = { "spring.desai" })
// @ComponentScan(basePackageClasses = Config.class)
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver setupViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/views");
		resolver.setSuffix(".jsp");
		// resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public void addResourcehandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Bean(name="Webdatasource")
	public DataSource getDataSource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate
				.lookup("java:/comp/env/jdbc/testDb");
		return dataSource;
	}
}
