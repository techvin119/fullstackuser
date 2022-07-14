package com.fse.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.user.model.Profiles;
import com.fse.user.service.UserService;


@RestController
@RequestMapping("/skill-tracker/api/v1/engineer")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/add-profile")
	public Profiles addProfile(@Valid @RequestBody Profiles profile) {
		return this.userService.addProfile(profile);
	}
	
	@PutMapping("/update-profile/{id}")
	public String updateProfile(@RequestBody Profiles profile, @PathVariable("id") int id ) {
		
		return this.userService.updateProfile(profile, id);
		
	}
	
	
}
