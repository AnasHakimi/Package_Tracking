<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title> 
<link rel="icon" type="image/x-icon" href="images/logo2.png">
<link rel="stylesheet" href="css/register.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
  <div class="wrapper">
    <h2>Sign Up Here</h2>
    <form action="Register" method="post">
    <div class="top">
      <div class="input-box">
        <input type="text" name="username" placeholder="Enter your username" required>
      </div>
      <div class="input-box">
        <input type="email" name="email" placeholder="Enter your email" required>
      </div>
      <div class="input-box">
        <input type="password" name="password" placeholder="Create password" required>
      </div>
       <div class="show-password">
      <input type="checkbox" id="show-password">
      <label for="show-password">Show Password</label>
    </div>
      <div class="select-box">
      <select name="gender">
      <option value="Male">Male</option>
      <option value="Female">Female</option>
     </select>
     </div>
     <div class="input-box">
        <input type="text" name="phonenumber"placeholder="Enter your phone number" required>
      </div>
      <div class="policy">
        <input type="checkbox">
        <h3>I accept all terms & condition</h3>
      </div>
      </div>
      <div class="center">
      <div class="input-box submit">
        <input type="Submit" value="Sign Up">
      </div>
      <div class="input-box reset">
      <input type="reset" value="Reset">
      </div>
      </div>
      <div class="bottom">
      <div class="text">
        <h3>Already have an account? <a href="login.jsp">Login now</a></h3>
      </div>
      </div>
    </form>
  </div>
  
  <script>
  document.getElementById('show-password').addEventListener('change', function() {
    const passwordInput = document.querySelector('input[name="password"]');
    if (this.checked) {
      passwordInput.type = 'text';
    } else {
      passwordInput.type = 'password';
    }
  });
</script>

</body>
</html>
