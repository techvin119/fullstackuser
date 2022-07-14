package com.fse.user.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvalidUserIdExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int userId;

}
