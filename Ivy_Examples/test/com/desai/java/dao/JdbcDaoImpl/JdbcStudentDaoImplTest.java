package com.desai.java.dao.JdbcDaoImpl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.desai.java.Student;
import com.desai.java.config.ConfigTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigTest.class, loader = AnnotationConfigContextLoader.class)
// @Transactional
// @TransactionConfiguration(defaultRollback = true)
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
