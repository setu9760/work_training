package spring.desai.mains;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import spring.desai.config.Config;


public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// AnnotationConfigWebApplicationContext context = new
		// AnnotationConfigWebApplicationContext();
		// context.register(MvcConfig.class, Config.class);
		//
		// Dynamic servlet = servletContext.addServlet("dispatcher",
		// new DispatcherServlet(context));
		// servlet.addMapping("/");
		// servlet.setLoadOnStartup(1);
	}
}
