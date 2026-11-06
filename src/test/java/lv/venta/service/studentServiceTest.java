package lv.venta.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lv.venta.model.Student;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.impl.CRUDStudentServiceImpl;

class studentServiceTest {
	
	@Mock
	private IStudentRepo studRepo;
	
	@InjectMocks
	private CRUDStudentServiceImpl studService;

	@BeforeEach
	public void initialize() {
		MockitoAnnotations.openMocks(this);
		
	}
	@Test
	public void testRetrieveAll() {
		Student st1 = new Student("Aivars", "Kiris");
		Student st2 = new Student("Janis", "Berzins");
		ArrayList<Student> testStudents= new ArrayList<>();
		testStudents.addAll(Arrays.asList(st1, st2));
		
		// simulejam kas notikas ja izsaucam repo funkcijas
		when(studRepo.count()).thenReturn(2l);
		when(studRepo.findAll()).thenReturn(testStudents);
		
		try {
		assertEquals(testStudents, studService.retrieveAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertDoesNotThrow(()-> studService.retrieveAll());
	}
	@Test
	public void testRetrieveAllThrowException() {
		when(studRepo.count()).thenReturn(0l);
		assertThrows(Exception.class, ()-> studService.retrieveAll());
		
	}

}
