$(document).ready(function() {

	$("#login_btn").click(function() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username && password) {

			var LoginModel = {
				username : username,
				password : password
			}

			console.log(LoginModel);
			// headers : {
			// "X-XSRF-TOKEN" : token,
			// },
			//			
			$.ajax({
				url : "/AuthenticateUser",
				data : JSON.stringify(LoginModel),
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