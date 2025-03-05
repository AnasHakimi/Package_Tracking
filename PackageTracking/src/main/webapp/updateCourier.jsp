<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit Courier</title>
<link rel="icon" type="image/x-icon" href="images/logo2.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
<link rel="stylesheet" href="css/styleadmin.css" />
</head>
<body>
 <aside class="sidebar">
    <div class="sidebar-header">
      <i class="fas fa-bars hamburger-icon"></i>
      <h2>Package Tracking</h2>
    </div>
    <ul class="sidebar-links">
      <h4>
        <span>Main Menu</span>
        <div class="menu-separator"></div>
      </h4>
      <li>
        <a href="CourierList">
          <span class="material-symbols-outlined"> dashboard </span>Dashboard</a>
      </li>
      <h4>
        <span>Account</span>
        <div class="menu-separator"></div>
      </h4>
      <li>
        <a href="logout"><span class="material-symbols-outlined"> logout </span>Logout</a>
      </li>
    </ul>
    <div class="user-account">
      <div class="user-profile">
        <img src="images/user.png" alt="Profile Image" />
        <div class="user-detail">
          <h3>Welcome!</h3>
          <span><%= (String) session.getAttribute("username") %></span>
        </div>
      </div>
    </div>
  </aside>
<nav>
    <img src="images/logo.png" alt="image not available" height="40px" width="170px" class="logo">
     
     <img src="images/user.png" class="user-pic" onclick="toggleMenu()">
     <div class="sub-menu-wrap" id="subMenu">
      <div class="sub-menu">
       <div class="user-info">
             <img src="images/user.png">
             <h3>Hello, <%= (String) session.getAttribute("username") %>!</h3>
       </div>
       <hr>
       <a href="logout" class="sub-menu-link">
          <img src="images/logout.png">
          <p>Logout</p>
          <span>></span>
       </a>
      </div>
     </div>
</nav>
 <div class="con">
        <h1>Courier Detail</h1>
      <form action="EditCourier" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="courier">Courier :</label>
        <input type="text" id="courier" name="courier_name" value="<c:out value="${c.courier_name}"/>">
    </div>
    <div class="form-group">
        <label for="courier-logo">Courier Logo :</label>
        <input type="file" id="courier-logo" name="courier_logo">
        <input type="hidden" name="current_courier_logo" value="<c:out value="${c.courier_logo}"/>">
    </div>
    <div class="form-group">
        <label for="courier-availability">Courier Availability:</label>
        <div class="availability">
            <input type="radio" id="available" name="courier_availability" value="Available" ${c.courier_availability == 'Available' ? 'checked' : ''}>
            <label for="available" class="available-label">Available ✔️</label>
            <input type="radio" id="unavailable" name="courier_availability" value="Unavailable" ${c.courier_availability == 'Unavailable' ? 'checked' : ''}>
            <label for="unavailable" class="unavailable-label">Unavailable ❌</label>
        </div>
    </div>
    <input type="hidden" name="courier_id" value="<c:out value="${c.courier_id}"/>"/><br><br>
    <button type="submit">SAVE</button>
</form>

  </div>
<footer style="margin-top: 8%;">
  <p>Copyright@Package Tracking CSC584
  <br>
  <p>Author:Anas Hakimi
</footer>

<script>
  let subMenu = document.getElementById("subMenu");
  
  function toggleMenu(){
	  subMenu.classList.toggle("open-menu");
  }
  
</script>


</body>
</html>
