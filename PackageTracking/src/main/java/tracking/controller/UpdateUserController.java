package tracking.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tracking.dao.UserDAO;
import tracking.model.User;

@WebServlet("/UpdateUser")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String phonenumber = request.getParameter("phonenumber");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setGender(gender);
        user.setPhonenumber(phonenumber);

        UserDAO userDao = new UserDAO();
        boolean updateSuccess = userDao.updateUserDetails(user);

        if (updateSuccess) {
        	request.getSession().setAttribute("updateSuccess", true);
            response.sendRedirect("ListShipments");
        } else {
            request.setAttribute("errorMessage", "Failed to update user details.");
            request.getRequestDispatcher("ListShipments").forward(request, response);
        }
    }
}
