<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Status Tracking</title>
<link rel="icon" type="image/x-icon" href="images/logo2.png">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
<script src="//www.tracking.my/track-button.js"></script>
</head>
<body>
<%

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

   String username = (String) session.getAttribute("username");
      if (username == null) {
            response.sendRedirect("login.jsp");
       }
%>
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
        <a href="index.jsp">
          <span class="material-symbols-outlined"> dashboard </span>Dashboard</a>
      </li>
      <h4>
        <span>Account</span>
        <div class="menu-separator"></div>
      </h4>
      <li>
        <a href="ListShipments"><span class="material-symbols-outlined"> account_circle </span>Profile</a>
      </li>
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
             <h3><%= (String) session.getAttribute("username") %></h3>
       </div>
       <hr>
       <a href="ListShipments" class="sub-menu-link">
          <img src="images/profile.png">
          <p>Profile</p>
          <span>></span>
       </a>
       <a href="logout" class="sub-menu-link">
          <img src="images/logout.png">
          <p>Logout</p>
          <span>></span>
       </a>
      </div>
     </div>
</nav>

<div id="embedTrack"></div>
<footer style="margin-top: 8%;">
  <p>Copyright@Package Tracking CSC584
  <br>
  <p>Author:Anas Hakimi
</footer>

<script>
//Function to get URL parameter by name
function getParameterByName(name) {
    const url = new URL(window.location.href);
    const params = new URLSearchParams(url.search);
    return params.get(name);
}

// Get the tracking number from the URL
const trackingNum = getParameterByName('tracking_num');

if (trackingNum) {
    // Embed the tracking number
    TrackButton.embed({
        selector: "#embedTrack",
        tracking_no: trackingNum
    });
} else {
    document.getElementById('embedTrack').innerText = 'No tracking number found.';
}

</script>

<script>
  let subMenu = document.getElementById("subMenu");
  
  function toggleMenu(){
	  subMenu.classList.toggle("open-menu");
  }
  
</script>
</body>
</html>
