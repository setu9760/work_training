package spring.desai.mains;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	private static final Log logger = LogFactory
			.getLog(StudentController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String subjectForm(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		Student student = new Student(1, "setu", 3);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("student", student);
		logger.info("studentForm handler");
		return "student";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String subjectResult(@ModelAttribute @Valid Student student,
			Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("message",
					"there was an error completing the request");
			return ("");
		} else {
			model.addAttribute("message", "Successfully saved student: "
					+ student);
		}
		logger.info("studentResult handler");
		return "result";
	}

}
