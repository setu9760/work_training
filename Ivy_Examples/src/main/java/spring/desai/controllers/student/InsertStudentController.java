package spring.desai.controllers.student;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.controllers.StudentController;
import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.StudentValidator;

@Controller
@RequestMapping(value = "student/insert")
public class InsertStudentController {

	private static final Logger logger = LogManager
			.getLogger(StudentController.class);

	@Autowired
	StudentDao studentDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentForm(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		logger.info("studentInsert get request handler");
		String formattedDate = dateFormat.format(date);

		Student student = new Student(1, "setu-web", 3);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("student", student);
		model.addAttribute("title", "Insert Student");

		return "student-insert";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String studentResult(@ModelAttribute("student") Student student,
			Model model, BindingResult bindingResult) {
		logger.info("studentInsert post request handler");
		StudentValidator validator = new StudentValidator();
		validator.validate(student, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("message",
					"there was an error completing the request");
			model.addAttribute("title", "Success");
			return ("student-insert");
		} else {
			studentDao.insert(student);
			model.addAttribute("message", "Successfully saved student: "
					+ student);
			model.addAttribute("title", "Error");
			return "result";
		}

	}

}
