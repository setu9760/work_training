package spring.desai.controllers.student;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private static final Log logger = LogFactory
			.getLog(StudentController.class);

	@Autowired
	StudentDao studentDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentForm(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		Student student = new Student(1, "setu-web", 3);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("student", student);
		model.addAttribute("title", "Insert Student");
		logger.info("studentForm handler");
		return "student-insert";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String studentResult(@ModelAttribute("student") Student student,
			Model model, BindingResult bindingResult) {

		StudentValidator validator = new StudentValidator();
		validator.validate(student, bindingResult);
		logger.info("studentResult handler");
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
