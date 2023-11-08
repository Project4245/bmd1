package com.bmd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmd.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/bmd")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public String test() {
		return "Your Code is working please use ......................";
	}


	// POST-create user
//	@PostMapping("/")
//	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
//		UserDto createUserDto = this.userService.createUser(userDto);
//		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
//	}

	// PUT- update user

//	@PutMapping("/{userId}")
//	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
//			@PathVariable("userId") Integer uid) {
//		UserDto updatedUser = this.userService.updateUser(userDto, uid);
//		return ResponseEntity.ok(updatedUser);
//	}

	// ADMIN
	// DELETE -delete user
//	@PreAuthorize("hasRole('PATIENT')")
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
//		this.userService.deleteUser(uid);
//		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
//	}

	// GET - user get
//	@GetMapping("/")
//	public ResponseEntity<List<UserDto>> getAllUsers() {
//		return ResponseEntity.ok(this.userService.getAllUsers());
//	}

	// GET - user get DOCTOR
//	@PreAuthorize("hasRole('DOCTOR')")
//	@GetMapping("/{userId}")
//	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
//		return ResponseEntity.ok(this.userService.getUserById(userId));
//	}

}
