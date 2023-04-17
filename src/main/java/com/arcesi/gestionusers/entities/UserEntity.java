package com.arcesi.gestionusers.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE")
	private Long code;
	@Column(name = "UNIQUE_CODE", insertable = true, nullable = false, unique = true, length = 50)
	private String codeUnique;
	@Column(length = 50, name = "FIRST_NAME", nullable = false, insertable = true)
	private String firstName;
	@Column(name = "LAST_NAME", length = 50, nullable = false, insertable = true)
	private String lastName;
	@Column(name = "EMAIL", length = 150, insertable = true, unique = true, nullable = false)
	private String email;
	
	@Column(name="PASSWORD",nullable = false,insertable = true,length = 255)
	private String passWord;
	@Column(name = "AGE", nullable = false, insertable = true)
	private int age;
	@Column(name = "IS_ACTIVE", insertable = true, nullable = false)
	private Boolean isActive;
	
	@CreatedDate
	@Column(name="CREATED_AT",nullable = false,insertable = true)
	private Instant createdAt;
	
	@LastModifiedDate
	@Column(name="UPDATED_ATE",nullable = true,insertable = true)
	private Instant updateAt;
}
