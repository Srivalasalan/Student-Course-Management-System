package edu.jsp.student_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.student_management.dto.CourseResponseDTO;
import edu.jsp.student_management.entity.Course;
import edu.jsp.student_management.service.CourseService;
import edu.jsp.student_management.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@Tag(name = "Course API",description = "Manage Course")
public class CourseController {
	
	private final CourseService service;
	
	@PostMapping("/save")
	@Operation(summary = "Creating New Course")
	public ResponseEntity<ResponseStructure<Course>> save(@RequestBody Course c){
		return service.saveData(c);
	}
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Get By ID")
	public ResponseEntity<ResponseStructure<Course>> findById(@PathVariable Long id){
		return service.findById(id);
	}
	
	@GetMapping("/findAll")
	@Operation(summary = "Get All Students with Pagination & Sorting")
	public ResponseEntity<ResponseStructure<List<CourseResponseDTO>>> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "id") String sortBy){
		return service.findAllData(page,size,sortBy);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete By Id")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Long id){
		return service.deleteById(id);
	}

}
