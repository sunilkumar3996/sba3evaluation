package com.eval.interview.interview.service;

import java.util.List;

import com.eval.interview.interview.dto.InterviewAttendeesDTO;
import com.eval.interview.interview.dto.InterviewCountDTO;
import com.eval.interview.interview.dto.InterviewDTO;

public interface InterviewService {

	public InterviewDTO saveInterview(InterviewDTO interviewDTO);
	
	public InterviewDTO getInterview(String technology);
	
	public InterviewDTO deleteInterview(String technology);
	
	public List<InterviewDTO> getAllInterviews();
	
	public InterviewDTO addAttendees(String interviewName,Integer userId);
	//public InterviewAttendeesDTO addAttendees(String interviewName,Integer userId);
	
	//public InterviewDTO getAttendees(String interviewName);
	public InterviewAttendeesDTO getAttendees(String interviewName);
	
	public InterviewDTO modifyStatus(String interviewName, String status);
	
	public InterviewCountDTO getInterviewCount();
	
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId);
	
	public List<InterviewDTO> getInterviewByInterviewor(String interviewerName);
}
