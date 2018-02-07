package com.lanou.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(HttpServletResponse response ) {
		
		try {
			response.sendRedirect("/home/fmain.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
