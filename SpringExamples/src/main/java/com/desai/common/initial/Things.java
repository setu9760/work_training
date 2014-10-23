package main.java.com.desai.common.initial;

public class Things {

	private Person person1;
	private Person person2;

	public Things() {
		// TODO Auto-generated constructor stub
	}

	public Things(Person person1, Person person2) {
		this.person1 = person1;
		this.person2 = person2;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

	@Override
	public String toString() {

		return person1.toString() + "\n" + person2.toString();
	}

}
