package lv.venta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GradeDTO {
	
	
	private String studentName;
	private String studentSurname;
	private String courseTitle;
	private int gradeValue;
	
	

}
