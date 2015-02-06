package spring.desai.controllers.student;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring.desai.controllers.StudentController;
import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.validators.StudentValidator;
import spring.desai.utils.GuidGeneratorException;

@Controller
@RequestMapping(value = "student/search")
public class SearchStudentController {

	private static final Logger logger = LogManager
			.getLogger(StudentController.class);

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private StudentValidator studentValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(studentValidator);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentSearch(Model model) throws GuidGeneratorException {
		logger.info("studentSearch get request handler");
		// Integer id = new Integer(18);
		Student student = new Student();
		model.addAttribute("student", student);
		// model.addAttribute("id", id);
		model.addAttribute("title", "Search student");
		return "student-search";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String studentSearchResult(@RequestParam("student") Student student,
			Model model, BindingResult result) {
		logger.info("studentSearch post request handler");
		if (result.hasErrors()) {
			model.addAttribute("message",
					"there was an error completing the request");
			model.addAttribute("title", "Error");
			return ("student-search");
		} else {
			Object obj = studentDao.findById(student.getStudent_id());
			if (obj != null) {
				logger.info(obj);
				model.addAttribute("message", "student found: " + obj);
				model.addAttribute("title", "Success");
			} else {
				model.addAttribute("message", "No student found for id: "
						+ student.getStudent_id());
				model.addAttribute("title", "Error");
			}
			return "result";
		}
	}
}
