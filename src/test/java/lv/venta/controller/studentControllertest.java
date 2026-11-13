package lv.venta.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import lv.venta.model.Student;
import lv.venta.service.impl.CRUDStudentServiceImpl;

class studentControllertest {
	
	@Mock
	private CRUDStudentServiceImpl studService;
	@InjectMocks
	private CRUDStudentController studController;
	private MockMvc mockMVC;
	
	@BeforeEach
	public void initalize() {
		MockitoAnnotations.openMocks(this);
		LocalValidatorFactoryBean validator
		= new LocalValidatorFactoryBean();
		validator.afterPropertiesSet();
		mockMVC = MockMvcBuilders.standaloneSetup(studController).setValidator(validator).build();
	}
	//testēt /student/crud/all
		@Test
		public void testAllController() {
			Student st1 = new Student("Karina", "Šķirmante");
			Student st2 = new Student("Jānis", "Bērziņš");
			
			ArrayList<Student> testStudents = new ArrayList<>();
			testStudents.addAll(Arrays.asList(st1, st2));
			
			try {
				when(studService.retrieveAll()).thenReturn(testStudents);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				mockMVC.perform(get("/student/crud/all"))
				.andExpect(content().
						contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name", is("Karina")))
				.andExpect(jsonPath("$[0].surname", is("Šķirmante")))
				.andExpect(jsonPath("$[1].name", is("Jānis")))
				.andExpect(jsonPath("$[1].surname", is("Bērziņš")))
				.andExpect(status().isOk());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		@Test
		public void testAddController() {
			
			Student st1 = new Student("Karina", "Šķirmante");
			Student st2 = new Student("Jānis", "Bērziņš");
			
			ArrayList<Student> testStudents = new ArrayList<>();
			testStudents.addAll(Arrays.asList(st1, st2));
			
			Student st3 = new Student("Oskars", "Domigais");
			
			try {
				when(studService.create(st3)).thenReturn(st3);
				testStudents.add(st3);
				
				when(studService.retrieveAll()).thenReturn(testStudents);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				mockMVC.perform(post("/student/crud/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{"
						+ "			\"name\": \"Oskars\","
						+ "			\"surname\": \"Domigais\""
						+ "		}")
						)
				
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[2].name", is("Oskars")))
				.andExpect(jsonPath("$[2].surname", is("Domigais")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
}
