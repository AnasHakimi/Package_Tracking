<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="tracking.model.Courier" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Dashboard</title>
<link rel="icon" type="image/x-icon" href="images/logo2.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
<link rel="stylesheet" href="css/styleadmin.css" />
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
          <span><%= username %></span>
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
             <h3>Hello, <%= username %>!</h3>
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

<div class="container">
    <h1>Courier List</h1>
    <a href="addCourier.jsp" class="btn btn-add-parcel">
            <span class="material-symbols-outlined">add</span> Add Courier
        </a>
    <table>
        <thead>
            <tr>
                <th>Courier ID</th>
                <th>Admin ID</th>
                <th>Courier</th>
                <th>Logo</th>
                <th>Availability</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Courier> couriers = (List<Courier>) request.getAttribute("couriers");
                if (couriers != null) {
                    for (Courier courier : couriers) {
            %>
            <tr>
                <td><%= courier.getCourier_id() %></td>
                <td><%= courier.getAdmin_id() %></td>
                <td><%= courier.getCourier_name() %></td>
                <td><img src="<%= courier.getCourier_logo() %>" alt="<%= courier.getCourier_name() %> Logo" height="40"></td>
                <td><%= courier.getCourier_availability() %></td>
                <td>
                    <a href="EditCourier?id=<%= courier.getCourier_id() %>"><i class="fas fa-edit"></i></a>
                    <a href="DeleteCourier?id=<%= courier.getCourier_id() %>" onclick="return confirm('Are you sure?');"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    
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

<script>
        window.onload = function() {
            var updateSuccess = '<%= request.getSession().getAttribute("editSuccess") %>';
            if (updateSuccess === 'true') {
                alert("Courier updated successfully!");
                // Clear the session attribute to avoid showing the popup again
                <% request.getSession().removeAttribute("editSuccess"); %>
            }
        }; 
        
</script>


</body>
</html>