package edu.jsp.student_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.jsp.student_management.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleStudentNotFoundException(StudentNotFoundException ex){
		ResponseStructure<String> rs=new ResponseStructure<String>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Failure");
		rs.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseStructure<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errors.put(error.getField(), error.getDefaultMessage());
	    });

	    ResponseStructure<Map<String, String>> rs = new ResponseStructure<>();
	    rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    rs.setMessage("Validation Failed");
	    rs.setData(errors);

	    return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}
}
