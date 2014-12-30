package spring.desai.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	private static final Logger logger = LogManager
			.getLogger(StudentController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentHome(Locale locale, Model model) {

		LocalDateTime dateTime = LocalDateTime.now();

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("title", "Student");
		logger.info("student handler");
		logger.info(dateTime);
		return "student";
	}

}
