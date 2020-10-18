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
import org.springframework.web.bind.annotation.RestController;

import com.eval.interview.interview.dto.InterviewAttendeesDTO;
import com.eval.interview.interview.dto.InterviewCountDTO;
import com.eval.interview.interview.dto.InterviewDTO;
import com.eval.interview.interview.exception.CustomException;
import com.eval.interview.interview.exception.model.ExceptionResponse;
import com.eval.interview.interview.service.InterviewService;
import com.eval.interview.interview.service.UserService;

@RestController
public class InterviewController {
	
	@Autowired
	UserService userService;
	@Autowired
	InterviewService interviewService;	

	@PostMapping("/interviews")
	public ResponseEntity<InterviewDTO> addInterview(@Valid @RequestBody InterviewDTO interviewDto,BindingResult result) {
		
	
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
					exception.setErrorMessage(exception.getErrorMessage()+" || "+err.getDefaultMessage());					
				}
			}
			exception.setErrorTimeStamp(LocalDateTime.now());
			throw exception;
		}
		
		return new ResponseEntity<InterviewDTO>(interviewService.saveInterview(interviewDto),HttpStatus.OK);
	}
	
	@PostMapping("/addAttendees/{interviewName}/{userId}")
	public ResponseEntity<InterviewDTO> addAttendees(@PathVariable("interviewName") String interviewName,@PathVariable("userId") Integer userId)
	{

		if(interviewService.getInterview(interviewName)==null || userService.getUserById(userId)==null)
			throw new CustomException(LocalDateTime.now(),"Association Exception","Inerview or user doesnt exist");
		
		if(interviewService.isAttendeeAddedToInterview(interviewName,userId))
			throw new CustomException(LocalDateTime.now(),"Users Exception","One or more of the user Ids in the request are already added to the "+interviewName+" Interview");
		return new ResponseEntity<InterviewDTO>(interviewService.addAttendees(interviewName,userId),HttpStatus.OK);
	}
	
	@GetMapping("/getAttendeesByInterviewName/{name}")
//	public ResponseEntity<InterviewDTO> getAttendees(@PathVariable("name") String intName)
//	{
//		if(interviewService.getInterview(intName)==null)
//			throw new CustomException(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
//		
//		return new ResponseEntity<InterviewDTO>(interviewService.getAttendees(intName),HttpStatus.OK);
//	}
	public ResponseEntity<InterviewAttendeesDTO> getAttendees(@PathVariable("name") String intName)
	{
		if(interviewService.getInterview(intName)==null)
			throw new CustomException(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<InterviewAttendeesDTO>(interviewService.getAttendees(intName),HttpStatus.OK);
	}
	
	@GetMapping("/searchInterviewByInterviewName/{interviewName}")
	public ResponseEntity<InterviewDTO> searchInterviewByName(@PathVariable("interviewName") String interviewName) {
		InterviewDTO interview=interviewService.getInterview(interviewName);
		if(interview==null)
			throw new CustomException(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<InterviewDTO>(interview,HttpStatus.OK);
	}
	
	@GetMapping("/searchInterviewByInterviewerName/{interviewerName}")
	public ResponseEntity<List<InterviewDTO>> searchInterview(@PathVariable("interviewerName") String interviewerName) {
		List<InterviewDTO> interviews=interviewService.getInterviewByInterviewor(interviewerName);
		if(interviews==null)
			throw new CustomException(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<List<InterviewDTO>>(interviews,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeInterview/{interviewName}")
	public ResponseEntity<InterviewDTO> removeInterview(@PathVariable("interviewName") String interviewName) {
		
		if(searchInterview(interviewName)==null)
			throw new CustomException(LocalDateTime.now(),"Interview Exception", interviewName+" Interview already Deleted Or does'nt exist");
		return new ResponseEntity<InterviewDTO>(interviewService.deleteInterview(interviewName),HttpStatus.OK);
	}
	
	@GetMapping("/interviews")
	public ResponseEntity<List<InterviewDTO>> searchAllInterviews()
	{
		return new ResponseEntity<List<InterviewDTO>>(interviewService.getAllInterviews(),HttpStatus.OK);
	}
	
	@PutMapping("/modifyStatus/{interviewName}/{status}")
	public ResponseEntity<InterviewDTO> modifyInterviewStatus(@PathVariable("interviewName") String interviewName,@PathVariable("status") String status)
	{
		InterviewDTO interview=interviewService.getInterview(interviewName);
		if(interview==null)
			throw new CustomException(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		return new ResponseEntity<InterviewDTO>(interviewService.modifyStatus(interviewName,status),HttpStatus.OK);
	}
	
	@GetMapping("/interviewCount")
	public ResponseEntity<InterviewCountDTO> getInterviewCount()
	{
		return new ResponseEntity<InterviewCountDTO>(interviewService.getInterviewCount(),HttpStatus.OK);
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
