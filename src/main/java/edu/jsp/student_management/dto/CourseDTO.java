package edu.jsp.student_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
	private Long id;
	private String title;
	private String description;
	private int duration;
	

}
