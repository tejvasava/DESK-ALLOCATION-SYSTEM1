package com.kcs.user.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;

	private String username;

	private String password;
}
