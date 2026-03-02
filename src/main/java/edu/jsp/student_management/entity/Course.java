package edu.jsp.student_management.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private int duration;
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students;
	
	
	public int getStudentCount() {
	    return students != null ? students.size() : 0;
	}

}
