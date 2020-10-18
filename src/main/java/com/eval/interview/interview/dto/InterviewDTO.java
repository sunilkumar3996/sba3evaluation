package com.eval.interview.interview.dto;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class InterviewDTO {
	
	private Integer interviewId;
	
	@NotBlank(message = "Interviewer Name should'nt be blank.")
	@Size(min = 5,max = 30,message = "Interviewer Name should be min 5 and max 30 characters.")
	private String interviewerName;
	
	@NotBlank(message = "Interview Name should'nt be blank.")
	@Size(min = 3,max = 30,message = "Interview Name should be min 3 and max 30 characters.")
	private String interviewName;
	
	@NotBlank(message = "User Skills should'nt be blank.")
	@Size(min = 5,max = 30,message = "User skills should be min 5 and max 30 characters.")
	private String userSkills;
	//private List<String> userSkills;
	
	@NotBlank(message = "Interview Status should'nt be blank.")
	@Size(min = 5, max = 100, message = "Interview status should be min 5 and max 100 characters.")
	private String interviewStatus;
	
	@NotBlank(message = "Remarks should'nt be blank.")
	@Size(min = 5, max = 100, message = "Remarks should be min 5 and max 100 characters.")
	private String remarks;	
	
	private LocalTime time;
	
	private LocalDate date;
	
	private List<UserDTO> users;

}
