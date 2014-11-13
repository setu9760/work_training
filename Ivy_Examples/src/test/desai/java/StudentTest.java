package desai.java;

import static org.junit.Assert.*;

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
import org.springframework.util.Assert;

import desai.java.config.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class }, loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class StudentTest {

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
	}

	@After
	public void tearDown() throws Exception {
		studentBean = studentBean2 = null;
	}

	@Test
	public void testStudent() {
		assertNotNull(studentBean);
		assertNotNull(studentBean2);
	}

	@Test
	public void testGetId() {
		assertNotNull(studentBean.getId());
		assertNotNull(studentBean2.getId());

		assertEquals(1, studentBean.getId());
		assertEquals(2, studentBean2.getId());

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

		studentBean2.setId(10);
		assertNotEquals(2, studentBean.getId());
		assertEquals(10, studentBean2.getId());

		assertEquals(studentBean.getId(), studentBean2.getId());

	}

	@Test
	public void testGetName() {
		assertNotNull(studentBean.getName());
		assertNotNull(studentBean2.getName());

		assertEquals("student 1", studentBean.getName());
		assertEquals("student 2", studentBean2.getName());

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
