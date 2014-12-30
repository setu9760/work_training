package spring.desai.controllers.student;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.controllers.StudentController;
import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;

@Controller
@RequestMapping(value = "student/search")
public class SearchStudentController {

	private static final Logger logger = LogManager
			.getLogger(StudentController.class);

	@Autowired
	private StudentDao studentDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentSearch(Model model) {
		logger.info("studentSearch get request handler");
		Integer id = new Integer(0);
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("id", id);
		model.addAttribute("title", "Search student");
		return "student-search";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String studentSearchResult(
			@ModelAttribute("student") Student student, Model model,
			BindingResult result) {
		logger.info("studentSearch post request handler");
		Object obj = studentDao.findById(student.getId());
		if (obj != null) {
			logger.info(obj);
			model.addAttribute("message", "student found: " + obj);
			model.addAttribute("title", "Success");
		} else {
			model.addAttribute("message",
					"No student found for id: " + student.getId());
			model.addAttribute("title", "Error");
		}
		return "result";
	}
}
