package spring.desai.controllers.tutor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.TutorDao;
import spring.desai.pojo.Tutor;

@Controller
@RequestMapping(value = "tutor/all")
public class AllTutorController {

	private static final Logger logger = LoggerFactory
			.getLogger(AllTutorController.class);

	@Autowired
	TutorDao tutorDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tutorAll(Model model) {
		List<Tutor> tutors = (List<Tutor>) tutorDao.getAll();
		model.addAttribute("tutors", tutors);
		model.addAttribute("title", "All Tutors");
		logger.info("tutorAll handler");
		return "tutor-all";
	}

}
