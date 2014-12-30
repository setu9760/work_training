package spring.desai.controllers.tutor;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.Subject;
import spring.desai.pojo.Tutor;

@Controller
@RequestMapping(value = "subject/all")
public class AllSubjectController {

	private static final Logger logger = Logger
			.getLogger(AllSubjectController.class);

	@Autowired
	SubjectDao subjectDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tutorAll(Model model) {
		List<Subject> subjects = (List<Subject>) subjectDao.getAll();
		model.addAttribute("subjects", subjects);
		model.addAttribute("title", "All Subjects");
		logger.info("subjectAll handler");
		return "subject-all";
	}

}
