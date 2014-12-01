package spring.desai.controllers.student;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "student/search")
public class SearchStudentController {

	private static final Log logger = LogFactory
			.getLog(StudentController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentSearch(Model model) {

		logger.info("studentSearch handler");
		return "student-search";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String studentSearchResult() {

		logger.info("studentSearch handler");
		return "result";
	}
}
