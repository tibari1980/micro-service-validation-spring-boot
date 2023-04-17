package com.arcesi.gestionusers.responses;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long code;
	
	private String codeUnique;

	private String firstName;

	private String lastName;

	private String email;

	private int age;

	private Boolean isActive;

	private Instant createdAt;

	private Instant updateAt;
}
