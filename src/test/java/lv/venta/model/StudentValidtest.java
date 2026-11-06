package lv.venta.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class StudentValidtest {
	
	private Validator validator;
	
	@BeforeEach
	public void initialize() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void validateGoodStudents() {
		Student student = new Student("Aivars", "Ķiris");
		Set<ConstraintViolation<Student>> problems =  validator.validate(student);
		
		assertTrue(problems.isEmpty());
	}
	@Test
	public void validateWrongStudent() {
		Student student = new Student("","123456");
		Set<ConstraintViolation<Student>> problems =  validator.validate(student);
		assertFalse(problems.isEmpty());// noteikti jabut validacijas parbaudei
		
	}

}
