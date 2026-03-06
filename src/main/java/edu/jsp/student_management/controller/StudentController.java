package edu.jsp.student_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.jsp.student_management.dto.StudentResponseDTO;
import edu.jsp.student_management.entity.Student;
import edu.jsp.student_management.service.StudentService;
import edu.jsp.student_management.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@Tag(name = "Student API",description = "Manage Students")
public class StudentController {
	
	private final StudentService service;
	
	@PostMapping("/save")
	@Operation(summary = "Create a new Student")
	public ResponseEntity<ResponseStructure<Student>> save(@Valid @RequestBody Student student){
		return service.saveData(student);
	}
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Get By Id")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> findById(@PathVariable Long id){
		return service.findById(id);
	}
	
	
	@GetMapping("/findAll")
	@Operation(summary = "Get All Students with Pagination & Sorting")
	public ResponseEntity<ResponseStructure<List<Student>>> findAll(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "id") String sortBy) {
	    return service.findAll(page, size, sortBy);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete By Id")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Long id){
		return service.deleteById(id);
	}
	
	@PostMapping("/{studentId}/courses/{courseId}")
	@Operation(summary = "Assigning Course To Student")
	public ResponseEntity<ResponseStructure<Student>> assignCourse(
	        @PathVariable Long studentId,
	        @PathVariable Long courseId) {

	    return service.assignCourse(studentId, courseId);
	}

}
