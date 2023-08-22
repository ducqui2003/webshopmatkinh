<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<!-- Website - www.codingnepalweb.com -->
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content=" Today in this blog you will learn how to create a responsive Login & Registration Form in HTML CSS & JavaScript. The blog will cover everything from the basics of creating a Login & Registration in HTML, to styling it with CSS and adding with JavaScript." />
    <meta
      name="keywords"
      content=" 
 Animated Login & Registration Form,Form Design,HTML and CSS,HTML CSS JavaScript,login & registration form,login & signup form,Login Form Design,registration form,Signup Form,HTML,CSS,JavaScript,
"
    />

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>Login & Signup Form HTML CSS | CodingNepal</title>
    <link rel="stylesheet" href="assets/css/style.css" />
    <script src="../custom-scripts.js" defer></script>
  </head>
  <body>
    <section class="wrapper">
      <p style = "color:white; text-align:center; ">${mess}</p>
      <div class="form signup">
        <header>Signup</header>
        <form action="./signup" name = "frm_signup" method ="post" onsubmit="return samePassword();">
          <input type="text" name = "email" placeholder="Email address" required />
          <input type="password" name = "password" placeholder="Password" required />
          <input type="password" name = "re_password" placeholder="Repeat Password" required />
          <div class="checkbox">
            <input type="checkbox" id="signupCheck" />
            <label for="signupCheck">I accept all terms & conditions</label>
          </div>
          <input type="submit" value="Signup" />
        </form>
      </div>

      <div class="form login">
        <header>Login</header>
        <form action="./login" method ="post">
          <input type="text" name ="email" placeholder="Email address" required />
          <input type="password" name ="password" placeholder="Password" required />
          <a href="forgot-password.jsp">Forgot password?</a>
          <input type="submit" value="Login" />
        </form>
      </div>

      <script>
        const wrapper = document.querySelector(".wrapper"),
          signupHeader = document.querySelector(".signup header"),
          loginHeader = document.querySelector(".login header");

        loginHeader.addEventListener("click", () => {
          wrapper.classList.add("active");
        });
        signupHeader.addEventListener("click", () => {
          wrapper.classList.remove("active");
        });
      </script>
      <script>
      	function samePassword(){
      		var pws = document.frm_signup.password.value;
      		var re_pws = document.frm_signup.re_password.value;
      		if(pws != re_pws){
      			alert("Password does not match");
      			return false;
      		}		
      	}
      </script>
    </section>
  </body>
</html>
