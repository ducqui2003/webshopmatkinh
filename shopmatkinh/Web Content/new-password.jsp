<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create a New Password</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/passwordchange.css">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-4 offset-md-4 form">
            	<p class="text-center" id="message"></p>
                <form name="frm" action="changepassword" method="POST" autocomplete="off">
                    <h2 class="text-center">New Password</h2>
                    <div class="form-group">
                        <input class="form-control" type="password" name="password" placeholder="Create new password" required>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" name="cpassword" placeholder="Confirm your password" required>
                    </div>
                    <div class="form-group">
                        <input class="form-control button" type="submit" name="change-password" value="Change">
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script>
      function samePassword(){
      	var pws = document.frm.password.value;
      	var re_pws = document.frm.cpassword.value;
      	if(pws != re_pws){
      		var message = document.getElementById("message");
      		message.innerHTML = "Passowrd does not match !";
      		return false;
      	}		
      }
</script>
</html>