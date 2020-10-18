package com.eval.interview.interview.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eval.interview.interview.dto.UserDTO;
import com.eval.interview.interview.exception.CustomException;
import com.eval.interview.interview.exception.model.ExceptionResponse;
import com.eval.interview.interview.service.InterviewService;
import com.eval.interview.interview.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	InterviewService interviewService;

	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDto,BindingResult result) {
		
		if(result.hasErrors())
		{
			CustomException exception=new CustomException();
			for(FieldError err:result.getFieldErrors())
			{
				if(exception.getErrorMessage()==null) {
				exception.setErrorCode(err.getCode());
				exception.setErrorMessage(err.getDefaultMessage());
				}
				else
				{
					exception.setErrorCode(exception.getErrorCode()+" || "+err.getCode());
					exception.setErrorMessage(exception.getErrorMessage()+ ", " +err.getDefaultMessage());
				}
			}
			exception.setErrorTimeStamp(LocalDateTime.now());
			throw exception;
		}
		
		return new ResponseEntity<UserDTO>(userService.addUserService(userDto),HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers(){ 
		return new ResponseEntity<List<UserDTO>>(userService.getAllUsersService(),HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{mobile}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("mobile") String mobile) {
		if(!userService.isUserPresent(mobile))
			throw new CustomException(LocalDateTime.now(),"Users Exception", "User with Mobile "+mobile+" already Deleted or user does'nt exist");
		return new ResponseEntity<UserDTO>(userService.deleteUserService(mobile),HttpStatus.OK);
	}
	
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> handler(CustomException ex) {
		
		ExceptionResponse exResponse =
				new ExceptionResponse(ex.getErrorMessage(), System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handler(Exception ex) {
		ExceptionResponse exResponse =
				new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		ResponseEntity<ExceptionResponse> response = 
				new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
		return response;
	}
}
