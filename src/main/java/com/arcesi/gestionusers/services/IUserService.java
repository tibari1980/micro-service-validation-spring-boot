package com.arcesi.gestionusers.services;

import java.util.List;

import com.arcesi.gestionusers.dtos.UserDTO;

public interface IUserService {

	public UserDTO addUser(UserDTO user);
	public UserDTO updateUser(UserDTO user, final Long idUser);
	public UserDTO findUserByid(final Long idUser);
	public UserDTO findByEmail(final String email);
	public UserDTO findByFirstNameAndLastNameIgnoreCase(String firstName,String lastName);
	public void deleteUser(final Long codeUser);
 
	public List<UserDTO> findAllUserByFirstName(final String patialFirstName,final  int page,final int limit);
}
