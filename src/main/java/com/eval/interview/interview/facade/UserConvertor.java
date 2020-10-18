package com.eval.interview.interview.facade;

import com.eval.interview.interview.dto.UserDTO;
import com.eval.interview.interview.entity.User;

public class UserConvertor {
	
	public static UserDTO userToUserDtoConverter(User user)
	{
		UserDTO userDto=new UserDTO();
		
		userDto.setUserId(user.getUserId());
		
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		
		userDto.setEmail(user.getEmail());
		userDto.setMobile(user.getMobile());
		
		return userDto;
	}

	public static User userDtoToUserConverted(UserDTO userDto)
	{
		User user=new User();
		
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setMobile(userDto.getMobile());
		return user;
	}
}
