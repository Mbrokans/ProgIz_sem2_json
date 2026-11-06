package lv.venta.repo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import lv.venta.model.Student;

@DataJpaTest
class StudentRepoTest {
	
	@Autowired
	private IStudentRepo studRepo;
	

	@Test
	public void saveNewStudent() {
		Student student = new Student("Aivars", "Kiris");
		Student studentFromDB = studRepo.save(student);
		assertEquals(student.getName(), studentFromDB.getName());
		assertEquals(student.getSurname(), studentFromDB.getSurname());
		assertTrue(studRepo.existsByNameAndSurname("Aivars", "Kiris"));
		assertFalse(studRepo.existsByNameAndSurname("sfaghw", "wfegr"));
		
	}

}
