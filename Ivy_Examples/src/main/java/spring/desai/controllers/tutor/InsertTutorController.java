package spring.desai.controllers.tutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.TutorDao;
import spring.desai.pojo.Tutor;
import spring.desai.pojo.validators.TutorValidator;

@Controller
@RequestMapping(value = "tutor/insert")
public class InsertTutorController {

	@Autowired
	private TutorDao tutorDao;

	@Autowired
	private TutorValidator tutorValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(tutorValidator);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String tutorForm(Model model) {

		return "tutor-insert";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String tutorResult(@ModelAttribute("tutor") Tutor tutor,
			Model model, BindingResult bindingResult) {

		return "result";
	}
}
