package spring.desai.controllers;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import spring.desai.dao.StudentDao;
import spring.desai.dao.SubjectDao;
import spring.desai.dao.TutorDao;
import spring.desai.pojo.validators.StudentValidator;
import spring.desai.pojo.validators.SubjectValidator;
import spring.desai.pojo.validators.TutorValidator;

public class BaseController {

	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	protected StudentDao studentDao;

	@Autowired
	protected SubjectDao subjectDao;

	@Autowired
	protected TutorDao tutorDao;

	@Autowired
	protected StudentValidator studentValidator;

	@Autowired
	protected TutorValidator tutorValidator;

	@Autowired
	protected SubjectValidator subjectValidator;

	protected Gson gson = new Gson();

	protected String jsonString;

	protected static final String JSON_RESPONSE = "json-response";

	protected String getformattedDate() {
		return LocalDateTime.now().toString("dd-MMMM-yyyy  kk-mm-ss  zzz");
	}

}
