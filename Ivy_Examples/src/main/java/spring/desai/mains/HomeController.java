package spring.desai.mains;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.SubjectDao;
import spring.desai.pojo.Subject;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger("mainAppLogger");

	static Log log = LogFactory.getLog(HomeController.class);

//	@Autowired
//	ApplicationContext context;
//
//	SubjectDao subjectDao1;
//
//	@Autowired
//	SubjectDao subjectDao2;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}." + locale);
		log.info("Welcome home! The client locale is {}." + locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);
		Subject subject = new Subject(3, "Comp");

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("subject", subject);
		log.info("returning home");
		logger.info("returning home");
		return "home";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String submit(@ModelAttribute("subject") @Valid Subject subject,
			Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("message",
					"there was an error completing the request");
			return ("home");
		} else {
			//subjectDao1 = (SubjectDao) context.getBean("subjectDao");
			//subjectDao1.insert(subject);
			model.addAttribute("message", "Successfully saved subject: "
					+ subject);
		}
		log.info("in the post method.");
		logger.info("in the post method.");
		return "result";
	}

}
