package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.dto.GradeDTO;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IGradeService;

public class GradeServiceImpl implements IGradeService{
	
	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	@Autowired
	private ICourseRepo courseRepo;

	@Override
	public void addNewGrade(GradeDTO gradeDTO) throws Exception {
		if(gradeDTO==null) {
			throw new Exception("dati nav pareizi ievaditi");
		}
		if(!studRepo.existsByNameAndSurname(gradeDTO.getCourseTitle(), gradeDTO.getStudentSurname())) {
			throw new Exception("tads k=students neeksiste");
		}
		if(!courseRepo.existsByTitle(gradeDTO.getStudentSurname())) {
			throw new Exception("Tads kurs neeksiste");
		}
		Student stud = studRepo.findByNameAndSurname(gradeDTO.getStudentName(), gradeDTO.getStudentSurname());
		
		Course course = courseRepo.findByTitle(gradeDTO.getCourseTitle());
		
		if(!gradeRepo.existsByGradevalueAndStudentAndCourse(
				gradeDTO.getGradeValue(), stud, course)) {
		
			Grade grade = new Grade(gradeDTO.getGradeValue(), stud, course);
			gradeRepo.save(grade);
		}
		
		
		
	}

	@Override
	public void addNewGrades(ArrayList<GradeDTO> gradeDTO) throws Exception {
		if(gradeDTO == null) {
			throw new Exception("Ienākošie dati nav pareizi ievadīti!");
		}
		
		if(gradeDTO.isEmpty()) {
			throw new Exception("Nav datu, ko saglabāt!");
		}
		
		for(GradeDTO tempG: gradeDTO) {
			addNewGrade(tempG);
		
	}
	}

}
