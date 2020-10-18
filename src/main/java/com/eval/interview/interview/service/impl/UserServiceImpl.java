package com.eval.interview.interview.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eval.interview.interview.dto.UserDTO;
import com.eval.interview.interview.entity.User;
import com.eval.interview.interview.facade.UserConvertor;
import com.eval.interview.interview.repository.InterviewRepository;
import com.eval.interview.interview.repository.UserRepository;
import com.eval.interview.interview.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepository;
	
	@Autowired
	InterviewRepository interviewrepository;
	
	@Override
	public UserDTO addUserService(UserDTO userDto) {
		// TODO Auto-generated method stub
		User user=UserConvertor.userDtoToUserConverted(userDto);
		return UserConvertor.userToUserDtoConverter(userrepository.save(user));
	}

	@Override
	public UserDTO deleteUserService(String mobile) {
		// TODO Auto-generated method stub
		User user=userrepository.findByMobile(mobile);
		if(user==null)
			return null;
		user.getInterviews().forEach(u->u.getUsers().remove(user));
		interviewrepository.saveAll(user.getInterviews());
		userrepository.deleteByMobile(mobile);
		return UserConvertor.userToUserDtoConverter(user);
	}

	@Override
	public List<UserDTO> getAllUsersService() {
		
		List<UserDTO> list=new ArrayList<UserDTO>();
		// TODO Auto-generated method stub
		for(User user:userrepository.findAll())
			list.add(UserConvertor.userToUserDtoConverter(user));
			
		return list;
	}

	@Override
	public boolean isUserPresent(String mobile) {
		// TODO Auto-generated method stub
		if(userrepository.findByMobile(mobile)==null)
			return false;
		return true;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=userrepository.findById(userId).orElse(null);
		if(user==null)
			return null;
		return UserConvertor.userToUserDtoConverter(user);
	}

}
