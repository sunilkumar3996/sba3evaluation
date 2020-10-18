package com.eval.interview.interview.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Data
@Entity
@Table(name = "interview_table")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "interviewId")
public class Interview {
	
	@Id  // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
	private Integer interviewId;
	
	private String interviewerName;
	
	private String interviewName;
	
	private String userSkills;
	
	private LocalTime time;
	
	private LocalDate date;
	
	private String interviewStatus;
	
	private String remarks;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="interviews_users_table",joinColumns = @JoinColumn(name="interviewId"),
	inverseJoinColumns = @JoinColumn(name="userId"))
	@JoinColumn(name = "interview_fk",referencedColumnName = "interviewId")
	private List<User> users = new ArrayList<User>();	
}
