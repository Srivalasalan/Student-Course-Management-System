package edu.jsp.student_management.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
	
	private Long id;
	private String name;
	private String email;
	private int age;
	
	private List<CourseDTO> courses;

}
