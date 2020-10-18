package com.eval.interview.interview.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

//	public ExceptionResponse(String message2, long currentTimeMillis, int value) {
//		// TODO Auto-generated constructor stub
//	}
	private String message;
	private Long timeStamp;
	private int status;
}
