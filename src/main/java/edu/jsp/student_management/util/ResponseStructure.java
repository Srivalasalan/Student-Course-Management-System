package edu.jsp.student_management.util;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private int statusCode;
	private String message;
	private T data;

}
