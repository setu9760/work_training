package desai.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import spring.desai.config.Config;
import spring.desai.pojo.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class }, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class StudentTest {

	private static final Logger log = LogManager.getLogger("testingLogger");

	@Autowired
	private ApplicationContext context;

	private Student studentBean;
	private Student studentBean2;

	@BeforeClass
	public static void beforeClass() throws Exception {
	}

	@AfterClass
	public static void afterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		studentBean = (Student) context.getBean("studentBean");
		studentBean2 = (Student) context.getBean("studentBean2");
		log.info("setUp method");
	}

	@After
	public void tearDown() throws Exception {
		studentBean = studentBean2 = null;
		log.info("tearDown method");
	}

	@Test
	public void testStudent() {
		assertNotNull(studentBean);
		assertNotNull(studentBean2);
		log.trace("constructor tested");
	}

	@Test
	public void testGetId() {
		assertNotNull(studentBean.getId());
		assertNotNull(studentBean2.getId());

		assertEquals(1, studentBean.getId());
		assertEquals(2, studentBean2.getId());
		log.info("abcd ");

		assertNotEquals(null, studentBean.getId());
		assertNotEquals(null, studentBean2.getId());
		assertNotEquals("string", studentBean.getId());
		assertNotEquals("string", studentBean2.getId());
		assertNotEquals(studentBean.getId(), studentBean2.getId());
	}

	@Test
	public void testSetId() {
		studentBean.setId(10);
		assertNotEquals(1, studentBean.getId());
		assertEquals(10, studentBean.getId());
		log.info("sdfds");
		studentBean2.setId(10);
		assertNotEquals(2, studentBean.getId());
		assertEquals(10, studentBean2.getId());

		assertEquals(studentBean.getId(), studentBean2.getId());
		log.info("dfsdf");
	}

	@Test
	public void testGetName() {
		assertNotNull(studentBean.getName());
		assertNotNull(studentBean2.getName());
		log.info("sfddas");
		assertEquals("student 1", studentBean.getName());
		assertEquals("student 2", studentBean2.getName());
		log.info("sfdfad");
		assertNotEquals(null, studentBean.getName());
		assertNotEquals(null, studentBean2.getName());
		assertNotEquals("string", studentBean.getName());
		assertNotEquals("string", studentBean2.getName());
		assertNotEquals(studentBean.getName(), studentBean2.getName());
	}

	@Test
	public void testSetName() {
		studentBean.setName("something");
		assertNotEquals("student 1", studentBean.getName());
		assertEquals("something", studentBean.getName());

		studentBean2.setName("something");
		assertNotEquals("student 2", studentBean2.getName());
		assertEquals("something", studentBean2.getName());

		assertEquals(studentBean.getName(), studentBean2.getName());
		studentBean.setName("a");
	}

	@Test
	public void testGetAge() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAge() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
