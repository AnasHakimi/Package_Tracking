<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Log In</title>
    <link rel="icon" type="image/x-icon" href="images/logo2.png">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
<body>
  <div class="container">
    <div class="cover">
      <div class="front">
        <img src="images/imgfront.jpg" alt="image not available">
        <div class="text">
          <span class="text-1">All-In-One <br> Package Tracking</span>
          <span class="text-2">Fast and Free</span>
        </div>
      </div>
    </div>
    <div class="forms">
        <div class="form-content">
          <div class="login-form">
            <div class="title">Log In</div>
          <form action="login" method="post">
            <div class="input-boxes">
              <div class="input-box">
                <i class="fas fa-user"></i>
                <input type="text" name="username" placeholder="Enter your username" required>
              </div>
              <div class="input-box">
                <i class="fas fa-lock"></i>
                <input type="password" name="password"  placeholder="Enter your password" required>
              </div>
              <div class="button input-box">
                <input type="submit" value="Log In">
              </div>
              <div class="button input-box">
                <input type="reset" value="Reset">
              </div>
              <div class="text sign-up-text">Don't have an account? <a href="register.jsp">Sign up now</a></div>
            </div>
        </form>
      </div>
       
    </div>
    </div>
  </div>
</body>
</html>