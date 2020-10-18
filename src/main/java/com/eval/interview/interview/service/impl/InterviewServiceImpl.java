package com.eval.interview.interview.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.interview.interview.dto.InterviewAttendeesDTO;
import com.eval.interview.interview.dto.InterviewCountDTO;
import com.eval.interview.interview.dto.InterviewDTO;
import com.eval.interview.interview.entity.Interview;
import com.eval.interview.interview.entity.User;
import com.eval.interview.interview.facade.InterviewConvertor;
import com.eval.interview.interview.repository.InterviewRepository;
import com.eval.interview.interview.repository.UserRepository;
import com.eval.interview.interview.service.InterviewService;

@Service
@Transactional
public class InterviewServiceImpl implements InterviewService {

	
	@Autowired
	public InterviewRepository interviewRepository;
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public InterviewDTO saveInterview(InterviewDTO interviewDto) {
		// TODO Auto-generated method stub
		Interview interview=InterviewConvertor.interviewDtoToInterviewConvertor(interviewDto);
		interview.setDate(LocalDate.now());
		interview.setTime(LocalTime.now());
		interviewRepository.save(interview); 
		return InterviewConvertor.interviewToInterviewDtoConvertor(interview);
		
	}

	@Override
	public InterviewDTO addAttendees(String interviewName,Integer userId) {
		// TODO Auto-generated method stub
		User user;		
		Interview interview=interviewRepository.findByInterviewName(interviewName);
		List<User> interviewusers=interview.getUsers();
		if(interviewusers==null)
			interviewusers=new ArrayList<User>();
			user=userRepository.findById(userId).orElse(null);
			interviewusers.add(user);
		interview.setUsers(interviewusers);
		interviewRepository.save(interview);
		return InterviewConvertor.interviewToInterviewDtoConvertor(interview);
	}

	@Override
//	public InterviewDTO getAttendees(String intName) {
//		// TODO Auto-generated method stub
//		return InterviewConvertor.interviewToInterviewDtoConvertor(interviewRepository.findByInterviewName(intName));
//	}
	public InterviewAttendeesDTO getAttendees(String intName) {
		// TODO Auto-generated method stub
		return InterviewConvertor.interviewToInterviewAttendeesDtoConvertor(interviewRepository.findByInterviewName(intName));
	}

	@Override
	public InterviewDTO getInterview(String technology) {
		// TODO Auto-generated method stub	
		if(interviewRepository.findByInterviewName(technology)==null)
			return null;
		return InterviewConvertor.interviewToInterviewDtoConvertor(interviewRepository.findByInterviewName(technology));
	}
	@Override
	public List<InterviewDTO> getInterviewByInterviewor(String name) {
		// TODO Auto-generated method stub	
		List<InterviewDTO> list= new ArrayList<InterviewDTO>();
		if(interviewRepository.findAllByInterviewerName(name)==null)
			return null;
		for(Interview interview:interviewRepository.findAllByInterviewerName(name))
		{
			list.add(InterviewConvertor.interviewToInterviewDtoConvertor(interview));
		}
		return list;
	}
	
	@Override
	public InterviewDTO deleteInterview(String technology) {
		// TODO Auto-generated method stub
		InterviewDTO interviewDto=getInterview(technology);
		interviewRepository.deleteByInterviewName(technology);
		return interviewDto;
	}

	@Override
	public List<InterviewDTO> getAllInterviews() {
		// TODO Auto-generated method stub
		List<InterviewDTO> intList=new ArrayList<>();
		for(Interview item:interviewRepository.findAll()) {
			intList.add(InterviewConvertor.interviewToInterviewDtoConvertor(item));
		}
		return intList;
	}

	@Override
	public InterviewDTO modifyStatus(String interviewName, String status) {
		// TODO Auto-generated method stub
		Interview interview=interviewRepository.findByInterviewName(interviewName);
		interview.setInterviewStatus(status);
		return InterviewConvertor.interviewToInterviewDtoConvertor(interview);
	}

	@Override
	public InterviewCountDTO getInterviewCount() {
		// TODO Auto-generated method stub
		InterviewCountDTO countDto=new InterviewCountDTO();
		countDto.setNoOfInterviews(interviewRepository.interviewCount());
		return countDto;
	}

	@Override
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId) {
		// TODO Auto-generated method stub
		Interview interview=interviewRepository.findByInterviewName(interviewName);
		boolean found=false;
		if(interview!=null)
		{
			List<User> users=interview.getUsers();
			for(User user:users) {
					if(userId==user.getUserId())
						found=true;				
			}
			
		}
		return found;
	}

}
