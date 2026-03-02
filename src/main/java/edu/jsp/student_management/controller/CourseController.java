package edu.jsp.student_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.student_management.dto.CourseResponseDTO;
import edu.jsp.student_management.entity.Course;
import edu.jsp.student_management.service.CourseService;
import edu.jsp.student_management.util.ResponseStructure;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
	
	private final CourseService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Course>> save(@RequestBody Course c){
		return service.saveData(c);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<Course>> findById(@PathVariable Long id){
		return service.findById(id);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<CourseResponseDTO>>> findAll(){
		return service.findAllData();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Long id){
		return service.deleteById(id);
	}

}
