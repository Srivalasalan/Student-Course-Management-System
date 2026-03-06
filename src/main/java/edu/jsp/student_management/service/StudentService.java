package edu.jsp.student_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.jsp.student_management.dao.CourseDao;
import edu.jsp.student_management.dao.StudentDao;
import edu.jsp.student_management.dto.CourseDTO;
import edu.jsp.student_management.dto.StudentResponseDTO;
import edu.jsp.student_management.entity.Course;
import edu.jsp.student_management.entity.Student;
import edu.jsp.student_management.exception.StudentNotFoundException;
import edu.jsp.student_management.util.ResponseStructure;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentDao sdao;
	private final CourseDao cdao;

	public ResponseEntity<ResponseStructure<Student>> saveData(Student student) {
		Student s = sdao.saveData(student);
		
		if (s != null) {
			ResponseStructure<Student> rs = new ResponseStructure<Student>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Student Data Saved Successfully");
			rs.setData(s);
			return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.CREATED);
		} else {
			ResponseStructure<Student> rs = new ResponseStructure<Student>();
			rs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rs.setMessage("Something Went Wrong ☹️☹️☹️");
			rs.setData(s);
			return new ResponseEntity<ResponseStructure<Student>>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<ResponseStructure<StudentResponseDTO>> findById(Long id) {
	    Student s = sdao.findById(id);

	    if (s == null) throw new StudentNotFoundException("Student Not Found With This ID: " + id);

	    StudentResponseDTO dto = new StudentResponseDTO();
	    dto.setId(s.getId());
	    dto.setName(s.getName());
	    dto.setEmail(s.getEmail());
	    dto.setAge(s.getAge());

	    List<CourseDTO> courseDto = (s.getCourses() == null ? List.of() : s.getCourses())
	            .stream()
	            .map(course -> {
	                Course c = (Course) course;  
	                CourseDTO cd = new CourseDTO();
	                cd.setId(c.getId());
	                cd.setTitle(c.getTitle());
	                cd.setDescription(c.getDescription());
	                cd.setDuration(c.getDuration());
	                return cd;
	            }).toList();

	    dto.setCourses(courseDto);

	    ResponseStructure<StudentResponseDTO> rs = new ResponseStructure<StudentResponseDTO>();
	    rs.setStatusCode(HttpStatus.OK.value());
	    rs.setMessage("Student Found With The ID!");
	    rs.setData(dto);
	    return new ResponseEntity<ResponseStructure<StudentResponseDTO>>(rs, HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<List<Student>>> findAll(int page, int size, String sortBy) {
	    
	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	    Page<Student> pageResult = sdao.findAll(pageable);
	    List<Student> students = pageResult.getContent();

	    ResponseStructure<List<Student>> rs = new ResponseStructure<>();

	    if (!students.isEmpty()) {
	        rs.setStatusCode(HttpStatus.OK.value());
	        rs.setMessage("Page " + (page + 1) + " of " + pageResult.getTotalPages()
	                    + " | Total Students: " + pageResult.getTotalElements());
	        rs.setData(students);
	        return new ResponseEntity<>(rs, HttpStatus.OK);
	    } else {
	        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
	        rs.setMessage("No Data Found!");
	        rs.setData(students);
	        return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(Long id) {
		boolean s = sdao.deleteById(id);
		ResponseStructure<String> rs = new ResponseStructure<String>();
		if (s) {

			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Student Deleted Successfully");
			rs.setData("Data Deleted Successfully: " + id);
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Student Not Found");
			rs.setData(null);
			return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
		}

	}

	public ResponseEntity<ResponseStructure<Student>> assignCourse(Long studentId, Long courseId) {
		ResponseStructure<Student> rs = new ResponseStructure<>();
		Student student = sdao.findById(studentId);
		Course course = cdao.findById(courseId);
		if (student == null || course == null) {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Student or Course Not Found");
			rs.setData(null);
			return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
		}
		if(student.getCourses()==null) student.setCourses(new ArrayList<>());
		student.getCourses().add(course);
		Student updatedStudent = sdao.saveData(student);
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Course Assigned Successfully");
		rs.setData(updatedStudent);

		return new ResponseEntity<>(rs, HttpStatus.OK);

	}

}
