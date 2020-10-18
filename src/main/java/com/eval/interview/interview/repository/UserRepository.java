package com.eval.interview.interview.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eval.interview.interview.entity.User;

@Repository
public interface UserRepository 
					extends JpaRepository<User, Integer>{
	
	
	public User findByMobile(String mobile);
	
	public Integer deleteByMobile(String mobile);
	
}

/**
 * Custom implementation
 * 1. Naming Convention : add a appropriatly named method in interface, implementation will be provided on the fly
 * 2. Query implementation
 * 3. Custom Interface with implementation which can be plugged with existing interface
 */
