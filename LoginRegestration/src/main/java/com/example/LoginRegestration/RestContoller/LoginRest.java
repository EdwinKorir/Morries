package com.example.LoginRegestration.RestContoller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LoginRegestration.Model.LoginModel;
import com.example.LoginRegestration.Model.ServerReponse;

@RestController
public class LoginRest {

	@RequestMapping("/AuthenticateUser")
	public ServerReponse getLogedUser(@RequestBody LoginModel loginModel) {
		ServerReponse serverReponse = new ServerReponse();
		String username = loginModel.getUsername();
		String password = loginModel.getPassword();
		if (username.equals("Edwin") && password.equals("12345")) {
			serverReponse.setStatus(200);
			serverReponse.setMessage("User authenticated Successfully");
		} else {
			serverReponse.setStatus(401);
			serverReponse.setMessage("Invalid Credentials");
		}

		return serverReponse;
	}
}
