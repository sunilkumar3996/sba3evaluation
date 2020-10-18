package com.eval.interview.interview.service;

import java.util.List;

import com.eval.interview.interview.dto.UserDTO;

public interface UserService {

	public UserDTO addUserService(UserDTO userDto);
	public UserDTO getUserById(Integer userId);
	public boolean isUserPresent(String mobile);
	public UserDTO deleteUserService(String mobile);
	public List<UserDTO> getAllUsersService();

}
