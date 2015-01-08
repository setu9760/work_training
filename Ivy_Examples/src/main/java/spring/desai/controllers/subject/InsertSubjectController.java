package spring.desai.controllers.subject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.SubjectDao;
import spring.desai.pojo.Subject;
import spring.desai.utils.GuidGeneratorException;

@Controller
@RequestMapping(value = "subject/insert")
public class InsertSubjectController {

	private static final Logger logger = Logger
			.getLogger(InsertSubjectController.class);

	@Autowired
	SubjectDao subjectDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String subjectForm(Model model) throws GuidGeneratorException {
		logger.info("subjectInsert get request handler");
		Subject subject = new Subject();
		model.addAttribute("subject", subject);
		model.addAttribute("title", "Insert Subject");
		return "subject-insert";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String subjectResult(@ModelAttribute("subject") Subject subject,
			Model model, BindingResult bindingResult) {
		logger.info("subjectInsert post request handler");
		
		
		return "result";
	}

}
