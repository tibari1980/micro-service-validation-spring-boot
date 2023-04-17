package com.arcesi.gestionusers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor @NoArgsConstructor @ToString
@EqualsAndHashCode @Data @Builder
public class UserRequest {


	
	private String firstName;

	
	private String lastName;

	
	private String email;
	
	private String passWord;

	private int age;
	
}
