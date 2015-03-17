package spring.desai.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger("mainAppLogger");

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}." + locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("title", "Home");
		logger.info("returning home");
		return "home";
	}

	@RequestMapping(value = "/reloadLog4J", method = RequestMethod.GET)
	public String reloadlog4J(Model model) {
		logger.info("Reloading Log4J prop file");
		try {
			String path = "C:\\log4j.properties";
			PropertyConfigurator.configure(path);
			model.addAttribute("message", "Successfully reloaded log4j");
			logger.info("reloaded successfully");
		} catch (Exception e) {
			model.addAttribute("message",
					"failed to reload log4j properties see error log");
			logger.error("log4j reloading failed", e);
		}
		return "result";
	}

}
