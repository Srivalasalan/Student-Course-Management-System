package edu.jsp.student_management.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.jsp.student_management.dao.CourseDao;
import edu.jsp.student_management.dto.CourseResponseDTO;
import edu.jsp.student_management.entity.Course;
import edu.jsp.student_management.util.ResponseStructure;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseDao cdao;

	public ResponseEntity<ResponseStructure<Course>> saveData(Course c) {
		ResponseStructure<Course> rs=new ResponseStructure<Course>();
		Course course=cdao.saveData(c);
		if(course!=null) {
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Course Added Successfully");
			rs.setData(course);
			return new ResponseEntity<ResponseStructure<Course>>(rs,HttpStatus.CREATED);
		}
		else {
			rs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rs.setMessage("Something Went Wrong!");
			rs.setData(course);
			return new ResponseEntity<ResponseStructure<Course>>(rs,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

	public ResponseEntity<ResponseStructure<Course>> findById(Long id) {
		ResponseStructure<Course> rs=new ResponseStructure<Course>();
		Course course=cdao.findById(id);
		if(course!=null) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Course Found With The ID!!");
			rs.setData(course);
			return new ResponseEntity<ResponseStructure<Course>>(rs,HttpStatus.OK);
		}
		else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Course Not Found With The ID! ☹️☹️☹️");
			rs.setData(course);
			return new ResponseEntity<ResponseStructure<Course>>(rs,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<CourseResponseDTO>>> findAllData() {
	    List<Course> courses = cdao.findAllData();
	    List<CourseResponseDTO> result = courses.stream().map(c -> {
	        CourseResponseDTO dto = new CourseResponseDTO();
	        dto.setId(c.getId());
	        dto.setTitle(c.getTitle());
	        dto.setDescription(c.getDescription());
	        dto.setDuration(c.getDuration());
	        dto.setStudentCount(c.getStudents() != null ? c.getStudents().size() : 0);
	        return dto;
	    }).toList();

	    ResponseStructure<List<CourseResponseDTO>> rs = new ResponseStructure<>();
	    if (!result.isEmpty()) {
	        rs.setStatusCode(HttpStatus.OK.value());
	        rs.setMessage("List Of All Courses....");
	        rs.setData(result);
	        return new ResponseEntity<>(rs, HttpStatus.OK);
	    } else {
	        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
	        rs.setMessage("No Courses Found!!");
	        rs.setData(result);
	        return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(Long id) {
		boolean res=cdao.deleteById(id);
		ResponseStructure<String> rs=new ResponseStructure<String>();
		
		if(res) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Course Deleted Successfully");
			rs.setData("Course Deleted with the Id: "+id);
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.OK);
		}
		else {
			rs.setStatusCode(HttpStatus.NOT_FOUND.value());
			rs.setMessage("Course Not Found With The ID");
			rs.setData(null);
			return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
		}
	}
}
