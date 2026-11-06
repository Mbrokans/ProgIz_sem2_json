package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

	@Test
	public void testStudentContructor() {
		Student student = new Student("Aivars", "Kiris");
		assertEquals("Aivars", student.getName());
		assertEquals("Kiris", student.getSurname());
		
	}
	@Test
	public void testString() {
		Student student = new Student("Aivars", "Kiris");
		assertNotNull(student.toString());
	}
	@Test
	public void testDefaultConstructor() {
		Student student= new Student();
		student.setName("Janis");
		student.setSurname("Berzins");
		assertEquals("Janis", student.getName());
		assertEquals("Berzins", student.getSurname());
	}

}
