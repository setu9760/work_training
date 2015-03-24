package spring.desai.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

import com.google.gson.Gson;

@Controller
@RequestMapping("api/json")
public class JsonResponseController {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private TutorDao tutorDao;

	private Gson gson = new Gson();
	private String jsonString;
	private static final String JSON_RESPONSE = "json-response";

	@RequestMapping(value = "/all-students", method = RequestMethod.GET)
	public String allStudents(Model model) {
		jsonString = gson.toJson((List<Student>) studentDao.getAll());
		model.addAttribute("jsonString", jsonString);
		return JSON_RESPONSE;
	}

	@RequestMapping(value = "/all-tutors", method = RequestMethod.GET)
	public String allTutors(Model model) {
		jsonString = gson.toJson((List<Tutor>) tutorDao.getAll());
		model.addAttribute("jsonString", jsonString);
		return JSON_RESPONSE;
	}

	@RequestMapping(value = "/all-subjects", method = RequestMethod.GET)
	public String allSubjects(Model model) {
		jsonString = gson.toJson((List<Subject>) subjectDao.getAll());
		model.addAttribute("jsonString", jsonString);
		return JSON_RESPONSE;
	}

	// TODO
	@RequestMapping(value = "/student-by-id", method = RequestMethod.POST)
	public String searchStudentById(Model model) {
		jsonString = gson.toJson((List<Student>) studentDao.findById("0"));
		model.addAttribute("jsonString", jsonString);
		return JSON_RESPONSE;
	}

	// TODO
	@RequestMapping(value = "/subject-by-id", method = RequestMethod.POST)
	public String searchSubjectById() {

		return JSON_RESPONSE;
	}

	// TODO
	@RequestMapping(value = "/tutor-by-id", method = RequestMethod.POST)
	public String searchTutorById() {

		return JSON_RESPONSE;
	}
}
