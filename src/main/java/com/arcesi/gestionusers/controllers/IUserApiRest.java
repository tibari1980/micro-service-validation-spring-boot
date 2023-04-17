package com.arcesi.gestionusers.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.gestionusers.requests.UserRequest;
import com.arcesi.gestionusers.responses.UserResponse;

public interface IUserApiRest {

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserResponse>> getAllUsers(
			@RequestParam(name = "partialFirstName", defaultValue = "") final String partialFirstName,
			@RequestParam(name = "page", defaultValue = " 0") final int page,
			@RequestParam(name = "limit", defaultValue = "10") final int limit);

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);

	@PutMapping(value = "{idUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest,
			@PathVariable("idUser") String idUser);


	@GetMapping(value="findUserById/{codeUser}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "codeUser") String codeUser);
	
	@GetMapping(value="findUserByCodeUnique/{codeUnique}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUserByCodeUnique(@PathVariable(name="codeUnique") final String codeUnique);


	@GetMapping(value="findUserByEmail/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable(name="email") final String email);
	
	@DeleteMapping(value="{codeUser}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name="codeUser") String codeUser);
}
