package spring.desai.controllers.student;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/student/all")
public class AllStudentController {

	private static final Log logger = LogFactory
			.getLog(StudentController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String studentAll() {

		logger.info("studentAll handler");
		return "student-all";
	}

}
