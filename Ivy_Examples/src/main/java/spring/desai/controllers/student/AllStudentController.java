package spring.desai.controllers.student;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.controllers.StudentController;
import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;

@Controller
@RequestMapping(value = "/student/all")
public class AllStudentController {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentController.class);

	@Autowired
	StudentDao studentDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentAll(Model model) {
		List<Student> students = (List<Student>) studentDao.getAll();
		model.addAttribute("students", students);
		model.addAttribute("title", "All Students");
		logger.info("studentAll handler");
		return "student-all";
	}

}
