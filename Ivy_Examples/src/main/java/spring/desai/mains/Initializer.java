package spring.desai.mains;

import javax.jms.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;


public class Initializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		Connection c;
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
