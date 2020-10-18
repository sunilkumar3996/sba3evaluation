package com.eval.interview.interview.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
	
	@NotNull
	@Column(unique=true)
	private Integer userId;
	
	@Size(min=5, max=30, message="First Name should be of minimum 5 characters and max 30 characters")
	private String firstName;
	
	@Size(min=3, max=25, message="Last Name should be of mini 3 characters and max 25 characters")
	private String lastName;
	
	@NotBlank
	@Email(message="Not valid email format")
	private String email;
	
	@Size(min=10, max=10, message="mobile number should contain 10 digits")
	private String mobile;
}
