package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.Student;
import lv.venta.model.enums.Degree;
import lv.venta.service.IFilterService;
import lv.venta.model.Course;
import lv.venta.model.Grade;

@RestController
@RequestMapping("/filter")
public class FilterController {
	
	@Autowired
	private IFilterService filterService;
	
	@GetMapping("/grade/student/{id}")//localhost:8080/filter/grade/student/1
	public ResponseEntity<?> getControllerGradesByStudentId(@PathVariable(name = "id") long id) {
		
		try
		{
			return new ResponseEntity<ArrayList<Grade>>
			(filterService.filterGradesByStudentId(id),HttpStatusCode.valueOf(200));
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/grade/course/{title}")//localhost:8080/filter/grade/course/Programmēšana JAVA
	public ResponseEntity<?> getControllerGradesByCourseTitle(@PathVariable(name = "title")
	String title) {
		try
		{
			return new ResponseEntity<ArrayList<Grade>>
		 (filterService.filterGradesByCourseTitle(title), HttpStatusCode.valueOf(200));
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}
	
	@GetMapping("/course/professor/{degree}")//localhost:8080/filter/course/professor/master
	public ResponseEntity<?> getControllerCourseByProfessorDegree(@PathVariable(name = "degree")
			Degree degree) {
		try
		{
			return new ResponseEntity<ArrayList<Course>>
		(filterService.filterCoursesByProfessorDegree(degree),HttpStatusCode.valueOf(200) );
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
		
	}
	
	@GetMapping("/student/failed")//localhost:8080/filter/student/failed
	public ResponseEntity<?> getControllerFailedStudents() {
		try
		{
			
			return new ResponseEntity<ArrayList<Student>> 
			( filterService.filterStudentsFailed(),
					HttpStatusCode.valueOf(200));
			
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),
					HttpStatusCode.valueOf(409));
		}
	}
	
	
	@GetMapping("/grades/10") //localhost:8080/filter/grades/10
	public ResponseEntity<?> getControllerGrades10() {
		try
		{
			return new ResponseEntity<ArrayList<Grade>>( 
				filterService.filterExcellentGrades(),
				HttpStatusCode.valueOf(200));
		
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),
					HttpStatusCode.valueOf(409));
		}
		
	}
	
	@GetMapping("/courses/creditpoints/{level}")//localhost:8080/filter/courses/creditpoints/5
	public ResponseEntity<?> getControllerCoursesCpLessThan
	(@PathVariable("level")int level) {
		try
		{
			return new ResponseEntity<ArrayList<Course>> 
			( filterService.filterCourseByCrediPointsLessThan(level),
			HttpStatusCode.valueOf(200));
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),
					HttpStatusCode.valueOf(409));
		}
	}
	
		
	

}
