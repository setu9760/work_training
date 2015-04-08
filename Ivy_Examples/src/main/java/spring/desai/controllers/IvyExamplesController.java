package spring.desai.controllers;

import org.apache.log4j.PropertyConfigurator;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "")
public class IvyExamplesController extends BaseController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("serverTime", getformattedDate());
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

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String studentHome(Model model) {

		String formattedDate = LocalDateTime.now().toString(
				"dd-MMMM-yyyy  kk-mm-ss  zzz");
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("title", "Student");
		logger.info("student handler");
		return "student";
	}

	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public String subjectHome(Model model) {

		model.addAttribute("serverTime", getformattedDate());
		model.addAttribute("title", "Subject");
		logger.info("subject handler");
		return "subject";
	}

	@RequestMapping(value = "/tutor", method = RequestMethod.GET)
	public String tutorHome(Model model) {

		model.addAttribute("serverTime", getformattedDate());
		model.addAttribute("title", "Tutor");
		logger.info("tutor handler");
		return "tutor";
	}
}
