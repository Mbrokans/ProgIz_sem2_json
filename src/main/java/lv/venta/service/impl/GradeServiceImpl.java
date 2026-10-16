package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.model.dto.GradeDTO;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IGradeService;

@Service
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
		if(gradeDTO.getGrvalue() < 1 || gradeDTO.getGrvalue() > 10) {
			throw new Exception("Atzīmes vērtībai jābūt robežās no 1 līdz 10");
		}
		if(!studRepo.existsByNameAndSurname(gradeDTO.getStudentName(), gradeDTO.getStudentSurname())) {
			throw new Exception("tads k=students neeksiste");
		}
		if(!courseRepo.existsByTitle(gradeDTO.getCourseTitle())) {
			throw new Exception("Tads kurs neeksiste");
		}
		Student stud = studRepo.findByNameAndSurname(gradeDTO.getStudentName(), gradeDTO.getStudentSurname());
		
		Course course = courseRepo.findByTitle(gradeDTO.getCourseTitle());
		
		if(!gradeRepo.existsBygrvalueAndStudentAndCourse(
				gradeDTO.getGrvalue(), stud, course)) {
		
			Grade grade = new Grade(gradeDTO.getGrvalue(), stud, course);
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

	@Override
	public ArrayList<GradeDTO> retrieveAll() throws Exception{
		if(gradeRepo.count()==0) {
			throw new Exception("Db nav ne viena atzime");
		}
		ArrayList<GradeDTO> result = new ArrayList<>();
		for(Grade tempG : gradeRepo.findAll()) {
			result.add(new GradeDTO(tempG.getStudent().getName()
					, tempG.getStudent().getSurname(),
					tempG.getCourse().getTitle(), tempG.getGrvalue()));
		}
		return result;
		
	}

}
