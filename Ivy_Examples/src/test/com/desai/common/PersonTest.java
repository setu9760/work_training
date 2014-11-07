package test.com.desai.common;

import static org.junit.Assert.*;

import main.com.desai.common.Person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PersonTest {

	private static final Logger log = LogManager.getLogger(PersonTest.class);

	Person person, expPerson;
	String expName;
	int expAge;

	@Before
	public void setUp() throws Exception {
		person = new Person();
		expPerson = new Person();
		expName = "testing";
		expAge = 18;
	}

	@After
	public void tearDown() throws Exception {
		person = null;
		expName = null;
		expAge = 0;
	}

	@Test
	public void testPerson() {
		log.info("testPerson no-args constructor");
		assertEquals(expPerson, person);
		assertFalse(person == null);
		assertFalse(person.equals(null));
		assertEquals(new Person(), person);
		assertEquals(new Person(expName, expAge), person);
	}

	@Test
	public void testPersonStringInt() {
		log.info("testPerson args constructor");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
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
	public void testEquals() {

	}

}
