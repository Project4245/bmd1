package com.bmd.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmd.config.AppConstants;
import com.bmd.entity.Role;
import com.bmd.entity.Users;
import com.bmd.exceptions.ResourceNotFoundException;
import com.bmd.exceptions.SingleRegistrationException;
import com.bmd.payload.UserDto;
import com.bmd.repository.RoleRepo;
import com.bmd.repository.UserRepo;
import com.bmd.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public boolean isMobileNumberRegistered(String mobileNumber) {
		return userRepo.findByMobileNumber(mobileNumber) != null;
	}

	public void registerUser(Users user) {
		userRepo.save(user);
	}

	public void storeOtp(String mobileNumber, String otp, LocalDateTime otpExpiration) {
		Users user = userRepo.findByMobileNumber(mobileNumber);
		user.setOtp(otp);
		user.setOtpExpiration(otpExpiration);
		userRepo.save(user);
	}

	public Users getUserByMobileNumber(String mobileNumber) {
		return userRepo.findByMobileNumber(mobileNumber);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		Users user = this.dtoToUser(userDto);
		Users savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setName(userDto.getName());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setOtp(userDto.getOtp());
		user.setEmail(userDto.getEmail());

		Users updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<Users> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}

	public Users dtoToUser(UserDto userDto) {
		Users user = this.modelMapper.map(userDto, Users.class);
		return user;
	}

	public UserDto userToDto(Users user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDto findByMobileNumber(String mobileNumber) {
		Users user = userRepo.findByMobileNumber(mobileNumber);
		return userToDto(user);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto, int rId) {

		Users user = this.modelMapper.map(userDto, Users.class);

		// encoded the password

//		 user.setOtp(this.passwordEncoder.encode(user.getOtp()));
		if (userRepo.existsByMobileNumber(userDto.getMobileNumber())) {
			throw new SingleRegistrationException("User with this mobile number is already registered.");
		} else {
			// roles

//		int id = 501;

			if (rId == AppConstants.DOCTOR) {
				Role role = this.roleRepo.findById(AppConstants.DOCTOR).get();

				user.getRoles().add(role);

				Users newUser = this.userRepo.save(user);
				return this.modelMapper.map(newUser, UserDto.class);

			} else if (rId == AppConstants.PATIENT) {
				Role role = this.roleRepo.findById(AppConstants.PATIENT).get();

				user.getRoles().add(role);

				Users newUser = this.userRepo.save(user);
				return this.modelMapper.map(newUser, UserDto.class);

			} else {
				System.out.println("Please Enter some value");

				return null;
			}
		}
	}
}
