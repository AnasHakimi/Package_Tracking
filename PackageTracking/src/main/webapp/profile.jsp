<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>User Profile</title>
<link rel="icon" type="image/x-icon" href="images/logo2.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
<link rel="stylesheet" href="css/style.css" />
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
<%
        if (session.getAttribute("editSuccess") != null) {
%>
        <script type="text/javascript">
            alert("Successfully Changed");
        </script>
<%
            session.removeAttribute("editSuccess");
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
<div class="con">
     <div class="user-detail-card">
        <h2>User Detail</h2>
        <div class="user-info">
            <img src="images/user.png" alt="User Image" style="width: 130px;">
            <div>
                <h3>${user.username}</h3>
                <p>${user.gender}</p>
                <p>${user.phonenumber}</p>
                <p>${user.email}</p>
            </div>
            <a href="#" class="btn btn-edit-profile" onclick="showEditForm()">
                <span class="material-symbols-outlined">edit</span>
            </a>
        </div>
    </div>

    <div id="editForm">
    <div id="editFormContent">
        <h3>Edit Profile</h3>
        <form action="UpdateUser" method="post">
            <input type="hidden" name="username" value="${user.username}" />
            <p>Email: <input type="text" name="email" value="${user.email}" /></p>
            <p>Gender: 
                <select name="gender">
                    <option value="Male" ${user.gender == 'Male' ? 'selected' : ''}>Male</option>
                    <option value="Female" ${user.gender == 'Female' ? 'selected' : ''}>Female</option>
                </select>
            </p>
            <p>Phone Number: <input type="text" name="phonenumber" value="${user.phonenumber}" /></p>
            <button type="submit">Save</button>
            <button type="button" onclick="hideEditForm()">Cancel</button>
        </form>
    </div>
   </div>

    <div class="tracking-history-card">
        <h2>Tracking History</h2>
        <a href="index.jsp" class="btn btn-add-parcel">
            <span class="material-symbols-outlined">add</span> Add Parcel
        </a>
       <c:choose>
    <c:when test="${not empty shipments}">
        <c:forEach items="${shipments}" var="s" varStatus="shipments">
            <div class="shipment-item">
                <img src="images/package.png" alt="Package Image">
                <div class="shipment-info">
                    <h3><c:out value="${s.title}"/></h3>
                    <p><c:out value="${s.tracking_num}"/></p>
                    <p>Status: <c:out value="${s.status_shipment}"/></p>
                    <p><c:out value="${s.date}"/> <c:out value="${s.time}"/></p>
                </div>
                <div class="shipment-actions">
                    <a href="viewStatus.jsp?tracking_num=<c:out value='${s.tracking_num}'/>" class="btn btn-primary">View</a>
                    <a href="EditTracking?itemID=<c:out value='${s.itemID}'/>" class="btn btn-primary">Edit</a>
                    <button class="btn btn-danger" id="<c:out value='${s.itemID}'/>" onclick="confirmation(this.id)">Delete</button>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>No Tracking Found.</p>
    </c:otherwise>
</c:choose>
    </div>
</div>
<footer style="margin-top: 8%;">
  <p>Copyright@Package Tracking CSC584
  <br>
  <p>Author:Anas Hakimi
</footer>

<script>
function confirmation(itemID) {
    console.log(itemID);
    var r = confirm("Are you sure you want to delete?");
    if (r == true) {
        location.href = 'DeleteTrackingController?itemID=' + itemID;
        alert("Shipment successfully deleted");
    } else {
        return false;
    }
}
</script>
<script>
let subMenu = document.getElementById("subMenu");

function toggleMenu() {
    subMenu.classList.toggle("open-menu");
}
</script>
<script>
function showEditForm() {
    document.getElementById('editForm').style.display = 'block';
}

function hideEditForm() {
    document.getElementById('editForm').style.display = 'none';
}

</script>

<script>
        window.onload = function() {
            var updateSuccess = '<%= request.getSession().getAttribute("updateSuccess") %>';
            if (updateSuccess === 'true') {
                alert("Profile updated successfully!");
                // Clear the session attribute to avoid showing the popup again
                <% request.getSession().removeAttribute("updateSuccess"); %>
            }
        }; 
        
</script>


</body>
</html>
