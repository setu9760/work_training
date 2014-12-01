package spring.desai.controllers.student;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.desai.dao.StudentDao;
import spring.desai.pojo.Student;
import spring.desai.pojo.Subject;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

	private static final Log logger = LogFactory
			.getLog(StudentController.class);

	@Autowired
	ApplicationContext context;

	@Autowired
	StudentDao studentDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentHome(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		logger.info("studentForm handler");
		return "student";
	}

}
