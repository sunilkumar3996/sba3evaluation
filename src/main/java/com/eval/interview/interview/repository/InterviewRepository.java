package com.eval.interview.interview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eval.interview.interview.entity.Interview;

@Repository
public interface InterviewRepository 
					extends JpaRepository<Interview, Integer>{
	
	public Interview findByInterviewName(String technology);
	
	public List<Interview> findAllByInterviewerName(String name);
	
	public Integer deleteByInterviewName(String technology);
	
	@Query("SELECT COUNT(x) from Interview x")
	public Integer interviewCount();
}

/**
 * Custom implementation
 * 1. Naming Convention : add a appropriatly named method in interface, implementation will be provided on the fly
 * 2. Query implementation
 * 3. Custom Interface with implementation which can be plugged with existing interface
 */
