package com.arcesi.gestionusers.dtos;

import java.io.Serializable;
import java.time.Instant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long code;
	
	private String codeUnique;

	@NotBlank(message = "First name must not be empty.")
	@Size(min = 4,max = 40,message ="FirstName must be between 4 to 40 characters")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "First Name require alphanumiric and space .")
	private String firstName;

	
	 
	@NotBlank(message="Last Name must not be empty")
	@Size(min = 4,max = 50,message = "LastName must be between 4 to 50 characters")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$",message = "LastName require alphannumiric and space.")
	private String lastName;

	@NotBlank(message = "Email User must not be empty")
  @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",message = "Email must be a syntactically correct email address")
	private String email;
	
	@NotBlank(message="Password must not be empty")
	@Size(min = 5 , max = 15 ,message = "Password must be between 5 to 16 characters.")
	private String passWord;

	
	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 100,message="Age shout not be greater than 100")
	private int age;

	private Boolean isActive;
	
	 
	private Instant createdAt;
	
	private Instant updateAt;
}
