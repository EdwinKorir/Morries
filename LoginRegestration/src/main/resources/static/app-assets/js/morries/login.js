$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	
	console.log(token);
	
	$("#login_btn").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username && password) {

			var LoginRequest = {
				username : username,
				password : password
			}

			console.log(LoginRequest);
			var role=['admin'];
					
//			$.ajax({
//				url : "/api/auth/signin",
//				 headers : {
//				 "X-XSRF-TOKEN" : token,
//				 },
//				data : JSON.stringify(LoginRequest),
//				type : "POST",
//				contentType : "application/json",
//				dataType : 'json',
//				success : function(result) {
//					console.log(result);
//					if (result.status != 200) {
//						alert(result.message);
//					} else {
//						window.location.replace("/Dashboard");
//					}
//				},
//				error : function(e) {
//					console.log("ERROR: ", e);
//				}
//			});
			
			var SignupRequest ={
				email:'edwin@gmail.com',
				username:'edwin',
				password:'12345678',
				role:role
				}
			$.ajax({
				url : "/api/auth/signup",
				 headers : {
				 "X-XSRF-TOKEN" : token,
				 },
				data : JSON.stringify(SignupRequest),
				type : "POST",
				contentType : "application/json",
				dataType : 'json',
				success : function(result) {
					console.log(result);
					if (result.status != 200) {
						alert(result.message);
					} else {
						window.location.replace("/Dashboard");
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});

		} else {
			alert("ERROR")
		}

	});

});