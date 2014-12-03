package spring.desai.controllers.student;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private static final Log logger = LogFactory
			.getLog(StudentController.class);

	@Autowired
	StudentDao studentDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentAll(Model model) {
		List<Student> students = (List<Student>) studentDao.getAll();
		logger.info("number of students: " + students.size());
		model.addAttribute("students", students);
		model.addAttribute("title", "All Students");
		logger.info("studentAll handler");
		return "student-all";
	}

}
