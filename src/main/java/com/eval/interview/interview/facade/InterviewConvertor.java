package com.eval.interview.interview.facade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.eval.interview.interview.dto.InterviewAttendeesDTO;
import com.eval.interview.interview.dto.InterviewDTO;
import com.eval.interview.interview.dto.UserDTO;
import com.eval.interview.interview.entity.Interview;
import com.eval.interview.interview.entity.User;

public class InterviewConvertor {
	
	public static Interview interviewDtoToInterviewConvertor(InterviewDTO interviewDto) {
		
		Interview interview=new Interview();
		
		interview.setDate(LocalDate.now());
		interview.setTime(LocalTime.now());
		
		interview.setInterviewerName(interviewDto.getInterviewerName());
		interview.setInterviewName(interviewDto.getInterviewName());
		interview.setInterviewStatus(interviewDto.getInterviewStatus());
		
		interview.setRemarks(interviewDto.getRemarks());
		
//		for(String skill:interviewDto.getUserSkills()) {
//			
//			if(interview.getUserSkills()==null)
//				interview.setUserSkills(skill);
//			else
//				interview.setUserSkills(interview.getUserSkills()+","+skill);
//				
//		}
		
		
		String skill = interviewDto.getUserSkills();
			
			if(interview.getUserSkills()==null)
				interview.setUserSkills(skill);
			else
				interview.setUserSkills(interview.getUserSkills()+","+skill);
		
		return interview;
	}
	
	public static InterviewDTO interviewToInterviewDtoConvertor(Interview interview)
	{
		InterviewDTO interviewDto=new InterviewDTO();
		
		//String[] skillset=interview.getUserSkills().split(",");
		
		List<UserDTO> list=new ArrayList<UserDTO>();
		
		interviewDto.setInterviewerName(interview.getInterviewerName());
		interviewDto.setInterviewId(interview.getInterviewId());
		interviewDto.setInterviewName(interview.getInterviewName());
		interviewDto.setInterviewStatus(interview.getInterviewStatus());
		
		interviewDto.setRemarks(interview.getRemarks());
		
		interviewDto.setTime(interview.getTime());
		interviewDto.setDate(interview.getDate());
		
		//interviewDto.setUserSkills(Arrays.asList(skillset));
		interviewDto.setUserSkills(interview.getUserSkills());
		
		if(interview.getUsers()!=null) {
		for(User user : interview.getUsers())
		{
			list.add(UserConvertor.userToUserDtoConverter(user));
		}
		interviewDto.setUsers(list);
		}
		//interviewDto.setUsers(interview.getUsers());

		return interviewDto;
	}
	
	public static InterviewAttendeesDTO interviewToInterviewAttendeesDtoConvertor(Interview interview)
	{
		InterviewAttendeesDTO interviewDto=new InterviewAttendeesDTO();
		//InterviewDTO interviewDto=new InterviewDTO();
		
		//String[] skillset=interview.getUserSkills().split(",");
		
		List<String> attendees=new ArrayList<String>();
		UserDTO userDto;
		
		//interviewDto.setInterviewerName(interview.getInterviewerName());
		//interviewDto.setInterviewId(interview.getInterviewId());
		interviewDto.setInterviewName(interview.getInterviewName());
		//interviewDto.setInterviewStatus(interview.getInterviewStatus());
		
		//interviewDto.setRemarks(interview.getRemarks());
		
		//interviewDto.setTime(interview.getTime());
		//interviewDto.setDate(interview.getDate());
		
		//interviewDto.setUserSkills(Arrays.asList(skillset));
		//interviewDto.setUserSkills(interview.getUserSkills());
		
		if(interview.getUsers()!=null) {
			for(User user : interview.getUsers())
			{
				userDto = UserConvertor.userToUserDtoConverter(user);
				attendees.add(userDto.getFirstName()+" "+userDto.getLastName());
			}
			interviewDto.setUserIds(attendees);
		}
		//interviewDto.setUsers(interview.getUsers());

		return interviewDto;
	}

}
