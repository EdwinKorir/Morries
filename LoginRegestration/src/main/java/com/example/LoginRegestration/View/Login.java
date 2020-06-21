package com.example.LoginRegestration.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
	@RequestMapping("/")
	private String getLoginView() {
		return "index";
	}

	@RequestMapping("/Regestration")
	private String getRegestrationView() {
		return "user-register";
	}

	@RequestMapping("/passwordReset")
	private String getForgotPassword() {
		return "user-forgot-password";
	}

	@RequestMapping("/Dashboard")
	private String getDashboard() {
		return "dashboard";
	}
}
