package com.desai.java.dao.JdbcDaoImpl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.desai.java.Student;
import com.desai.java.config.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
// @TransactionConfiguration(defaultRollback = true)
// @Transactional
public class JdbcStudentDaoImplTest {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private JdbcStudentDaoImpl studentDao;

	@Autowired
	private Student student;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		assertNotNull(student);
		assertNotNull(studentDao);
		assertNotNull(studentDao.insert(student));
		assertEquals(1, (int) studentDao.insert(student));
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAssociatedSubjects() {
		fail("Not yet implemented");
	}

}
