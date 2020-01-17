package com.nc.naucnicentar.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.impl.identity.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nc.naucnicentar.util.LoginDto;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	IdentityService identityService;
	@PostMapping(path = "/attemp", produces = "application/json")
	public ResponseEntity login(@RequestBody LoginDto loginDto){
		boolean identified = identityService.checkPassword(loginDto.getUsername(), loginDto.getPassword());
		if (identified == true){
			return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
		}else{
			return new ResponseEntity<>( HttpStatus.NOT_FOUND);

		}
		
		
		
		
	}
}
