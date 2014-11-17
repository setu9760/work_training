package spring.desai.mains;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.pojo.Subject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		Subject subject = new Subject(3, "Comp");

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("subject", subject);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submit(@ModelAttribute @Valid Subject subject, Model model,
			BindingResult bindingResult) {
		model.addAttribute("message", "Successfully saved subject: " + subject);
		if (bindingResult.hasErrors())
			return ("home");
		return "result";
	}

	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public String subjectForm(Model model) {

		logger.info("subjectForm handler");
		return "home";
	}

	@RequestMapping(value = "/subject", method = RequestMethod.POST)
	public String subjectResult(@ModelAttribute @Valid Subject subject,
			Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return ("");
		logger.info("subjectResult handler");
		return "result";
	}

}
