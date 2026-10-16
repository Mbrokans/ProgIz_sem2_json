package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.model.dto.GradeDTO;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IGradeService;

public class GradeServiceImpl implements IGradeService{
	
	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;

	@Override
	public void addNewGrade(GradeDTO gradeDTO) throws Exception {
		if(gradeDTO==null) {
			throw new Exception("dati nav pareizi ievaditi");
		}
		
		
		
	}

	@Override
	public void addNewGrades(ArrayList<GradeDTO> gradeDTO) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
