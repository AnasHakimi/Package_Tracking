package tracking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tracking.dao.UserDAO;
import tracking.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/Register")
public class RegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public RegistrationController() {
        super();
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User u = new User();
        
		u.setUsername(request.getParameter("username"));
		u.setEmail(request.getParameter("email"));
		u.setPassword(request.getParameter("password"));
		u.setGender(request.getParameter("gender"));
		u.setPhonenumber(request.getParameter("phonenumber"));

		boolean isRegistered = userDAO.registerUser(u);

        if (isRegistered) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Registration successful!');");
            out.println("window.location.href = 'login.jsp';");
            out.println("</script>");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            RequestDispatcher view = request.getRequestDispatcher("register.jsp");
            view.forward(request, response);
        }
	}
		
		 
}
