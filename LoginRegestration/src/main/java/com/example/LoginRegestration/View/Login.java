package com.example.LoginRegestration.View;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
	@RequestMapping("/")
	private String getLoginView(Model model, HttpServletRequest request) {

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("_csrf.token", token.getToken());
		model.addAttribute("_csrf.parameterName", token.getParameterName());
		model.addAttribute("_csrf_header", token.getHeaderName());

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
