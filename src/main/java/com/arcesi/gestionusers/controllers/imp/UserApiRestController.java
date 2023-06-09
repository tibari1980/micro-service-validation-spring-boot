package com.arcesi.gestionusers.controllers.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.gestionusers.controllers.IUserApiRest;
import com.arcesi.gestionusers.dtos.UserDTO;
import com.arcesi.gestionusers.enums.ErrorsCodeEnumeration;
import com.arcesi.gestionusers.exceptions.InvalidEntityException;
import com.arcesi.gestionusers.requests.UserRequest;
import com.arcesi.gestionusers.responses.UserResponse;
import com.arcesi.gestionusers.services.IUserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/users/")
@AllArgsConstructor
@Slf4j
public class UserApiRestController implements IUserApiRest {

	private IUserService iUserService;
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<List<UserResponse>> getAllUsers(String partialFirstName, int page, int limit) {
		log.info("Inside methode getAllUsers of UserApiRestController partialFirstName : {} , page : {} , limit : {}",
				partialFirstName, page, limit);
		List<UserDTO> userDtos = iUserService.findAllUserByFirstName(partialFirstName, page, limit);
		List<UserResponse> userResponses = userDtos.stream().map(dto -> modelMapper.map(dto, UserResponse.class))
				.collect(Collectors.toList());
		if(userResponses.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserResponse>>(userResponses,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
		log.info("Inside methode createUser of UserApiRestController  userRequest : {}", userRequest.toString());

		UserDTO dto = modelMapper.map(userRequest, UserDTO.class);
		UserDTO dtoSaved = iUserService.addUser(dto);
		UserResponse userResponse = modelMapper.map(dtoSaved, UserResponse.class);
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserResponse> updateUser(UserRequest userRequest, String idUser) {
		log.info("Inside methode updateUser of UserApiRestController  userRequest: {},  code User : {}", userRequest.toString(),idUser);
		if(StringUtils.isBlank(idUser) || !StringUtils.isNumeric(idUser))
		{
			log.error("Id user is not valid try again : `{}`",idUser);
			throw new InvalidEntityException("Code User  :`"+idUser +"` is not valid try again !");
		}
		UserDTO userDTO=modelMapper.map(userRequest, UserDTO.class);
		UserDTO userDto=iUserService.updateUser(userDTO, Long.parseLong(idUser));
		
		return new ResponseEntity<UserResponse>(modelMapper.map(userDto, UserResponse.class),HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<UserResponse> getUserById(String codeUser) {
		log.info("Inside methode getUserById of UserApiRestController  code user : {} ",codeUser);
		if(StringUtils.isBlank(codeUser) || !StringUtils.isNumeric(codeUser)) {
			log.error("Code user : `{}` is not valid try again ",codeUser);
			throw new InvalidEntityException("Code user : `"+codeUser+"` is not valid try again !",ErrorsCodeEnumeration.USER_PARAM_ID_USER_NOT_VALID);
		}
		UserDTO userDto=iUserService.findUserByid(Long.parseLong(codeUser));
		return new ResponseEntity<UserResponse>(modelMapper.map(userDto, UserResponse.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> getUserByCodeUnique(String codeUnique) {
	    log.info("Inside methode getUserByCodeUnique  code unique  : {} ",codeUnique);
	    if(StringUtils.isBlank(codeUnique)) {
	    	log.error("User code unique : ` {} `  is not valid try again ",codeUnique);
	    	throw new InvalidEntityException("Code unique : ` "+ codeUnique +"`  is not valide try again", ErrorsCodeEnumeration.USER_PARAM_ID_USER_NOT_VALID);
	    }
	    UserDTO dto=iUserService.findUserByCodeUnique(codeUnique);
		return new ResponseEntity<UserResponse>(modelMapper.map(dto, UserResponse.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> getUserByEmail(String email) {
		 log.info("Inside methode getUserByEmail  email : {} ",email);
		    if(StringUtils.isBlank(email)) {
		    	log.error("User email : ` {} `  is not valid try again ",email);
		    	throw new InvalidEntityException("Email : ` "+ email +"`  is not valide try again", ErrorsCodeEnumeration.USER_PARAM_ID_USER_NOT_VALID);
		    }
		    UserDTO dto=iUserService.findByEmail(email);
			return new ResponseEntity<UserResponse>(modelMapper.map(dto, UserResponse.class),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteUser(String codeUser) {
		 log.info("Inside methode deleteUser of UserApiRestController  id : {} ",codeUser);
		    if(StringUtils.isBlank(codeUser)) {
		    	log.error("User id : ` {} `  is not valid try again ",codeUser);
		    	throw new InvalidEntityException("Code user : ` "+ codeUser +"`  is not valid try again", ErrorsCodeEnumeration.USER_PARAM_ID_USER_NOT_VALID);
		    }
		    iUserService.deleteUser(Long.parseLong(codeUser) );
		    return new ResponseEntity<Object>("User  with  : ` "+codeUser+" ` Deleted successfully", HttpStatus.NO_CONTENT);
	}

}
