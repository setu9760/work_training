package spring.desai.mains;

import java.util.Locale;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public String subjectForm(Model model) {

		return "home";
	}

	@RequestMapping(value = "/subject", method = RequestMethod.POST)
	public String subjectResult(@ModelAttribute Subject subject, Model model) {

		return "result";
	}

}
