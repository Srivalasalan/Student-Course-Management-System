package edu.jsp.student_management.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name cannot be empty")
	private String name;
	@Column(unique = true)
	@Email(message = "Invalid Email Format")
	@NotBlank(message = "Email cannot be empty")
	private String email;
	@Min(value = 18, message = "Age must be at least 18")
	private int age;
	@ManyToMany
	@JoinTable(name="student_course",joinColumns =@JoinColumn(name="student_id"),inverseJoinColumns = @JoinColumn(name="course_id") )
	private List<Course> courses;

}
