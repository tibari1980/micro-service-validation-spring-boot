package com.arcesi.gestionusers.services.imp;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arcesi.gestionusers.dtos.UserDTO;
import com.arcesi.gestionusers.entities.UserEntity;
import com.arcesi.gestionusers.enums.ErrorsCodeEnumeration;
import com.arcesi.gestionusers.exceptions.EntityNotFoundException;
import com.arcesi.gestionusers.exceptions.InvalidEntityException;
import com.arcesi.gestionusers.repositories.UserRepository;
import com.arcesi.gestionusers.services.IUserService;
import com.arcesi.gestionusers.validators.ObjectValidator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImp implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectValidator validator;

	@Override
	public UserDTO addUser(UserDTO userDto) {
		log.info("Inside methode addUser of UserServiceImp  : userDto : {}", userDto);
		userDto.setCreatedAt(Instant.now());
		Set<String> violations = validator.validate(userDto);
		if (!violations.isEmpty()) {
			log.error("There are errors while saving ", violations);
			throw new InvalidEntityException("There are arrors while saving  user" ,ErrorsCodeEnumeration.USER_NOT_VALID, violations);
		}
		userDto.setCodeUnique(UUID.randomUUID().toString());
		userDto.setIsActive(Boolean.TRUE);
		UserEntity entity = modelMapper.map(userDto, UserEntity.class);
		UserEntity bean = userRepository.save(entity);
		log.info("User created successfully !!",bean.toString());
		return modelMapper.map(bean, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Long idUser) {
		log.info("Inside methode updateUser of UserServiceImp  user dto : {}  code user : {}", userDto.toString(),idUser);
		Set<String> violations=validator.validate(userDto);
		if(!violations.isEmpty()) {
			 log.error("There are errors while updating user try again : {}",violations);
			 throw new InvalidEntityException("There are errors while updating user  try again" ,ErrorsCodeEnumeration.USER_NOT_VALID, violations);
		}
		//check if user exist by email
		UserEntity checkUser=userRepository.findById(idUser).orElseThrow(()->new EntityNotFoundException("User  : ` "+idUser+" ` could not found in our data base try again",ErrorsCodeEnumeration.USER_NOT_FOUND));
		checkUser.setAge(userDto.getAge() );
		checkUser.setFirstName(userDto.getFirstName());
		checkUser.setLastName(userDto.getLastName());
		checkUser.setPassWord(userDto.getPassWord());
		checkUser.setUpdateAt(Instant.now());
		checkUser.setEmail(userDto.getEmail());
		checkUser.setPassWord(userDto.getEmail());
		UserEntity userSaved=userRepository.save(checkUser);
		log.info("User updated successfully ", userSaved.toString());
		return modelMapper.map(userSaved, UserDTO.class) ;
	}

	@Override
	public UserDTO findUserByid(Long idUser) {
		log.info("Inside methoe findUserById of UserServiceImp  code user : {}",idUser);
		UserEntity findUser=userRepository.findById(idUser).orElseThrow(()->new EntityNotFoundException("User  id  : ` "+idUser+ " ` could not found in our data base !! ",ErrorsCodeEnumeration.USER_NOT_FOUND));
		
		log.info("User with code : `{}` find successfully  !!!  userEntity : {} ",idUser, findUser.toString());
		return modelMapper.map(findUser, UserDTO.class);
	}

	@Override
	public UserDTO findByEmail(String email) {
		log.info("Inside methoe findUserByEmail of UserServiceImp  email : {}",email);
		Optional<UserEntity> userEntity=userRepository.findByEmailIgnoreCase(email);
		userEntity.orElseThrow(()->new EntityNotFoundException("User  email  : `  "+email + " ` could not found in our data base !! ",ErrorsCodeEnumeration.USER_NOT_FOUND));
		log.info("User with email : `{}` find successfully  !!!  userEntity : {} ",email, userEntity.get().toString());
		return modelMapper.map(userEntity.get(), UserDTO.class);
	}

	@Override
	public UserDTO findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long codeUser) {
		log.info("Inside deleteUser of UserServiceImp   code User : ",codeUser);
		UserDTO dto=findUserByid(codeUser);
         if(dto!=null) {
        	 userRepository.deleteById(dto.getCode());
         }
         log.info("User with  code  :  ` {}`  deleted successfully !!!!",codeUser);
		
	}

	@Override
	public List<UserDTO> findAllUserByFirstName(String partialFirstName, int page, int limit) {
		log.info("Inside methode findAllUserByFirstName of UserServiceImp  partialFirstName: {} , page : {} , limit : {}",partialFirstName,page,limit);
		if(page >0) {
			page=page-1;
		}
		Pageable pageable=PageRequest.of(page, limit, Sort.by("code").descending());
		Page<UserEntity> usersPage=userRepository.findByFirstNameContainingIgnoreCase(partialFirstName,pageable);
		List<UserEntity> lstUsers=usersPage.getContent();
		List<UserDTO> lstUserDtos=lstUsers.stream().map(user->modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
		log.info("All users  : ` {} ` find successfully withc partialFirstName : `{}`  !!! ", lstUserDtos.size(), partialFirstName);
		return lstUserDtos;
	}

	@Override
	public UserDTO findUserByCodeUnique(String codeUnique) {
	     log.info("Inside methode findUserByCodeUnique of UserServiceImp : code unique : {}",codeUnique);
	     
	     Optional<UserEntity> user=userRepository.findByCodeUniqueIgnoreCase(codeUnique);
	     if(!user.isPresent()) {
	    	 log.error("User code unique  : `{}` not found in our data base try again",codeUnique);
	    	 throw new EntityNotFoundException("User : ` "+codeUnique+"` not found in our data base try again ",ErrorsCodeEnumeration.USER_NOT_FOUND);
	     }
	     log.info("User with code unique  : ` {} ` find successfully  !!!  userEntity : {} ",codeUnique, user.get().toString());
		return modelMapper.map(user.get(), UserDTO.class);
	}

}
