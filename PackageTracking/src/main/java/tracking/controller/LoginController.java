package tracking.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tracking.dao.UserDAO;
import tracking.model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public LoginController() {
        super();
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        User u = new User();
        u.setUsername(request.getParameter("username"));
        u.setPassword(request.getParameter("password"));
        String username = request.getParameter("username");
       
        boolean isValidUser = userDAO.validateUser(u);
        boolean isValidAdmin = userDAO.validateAdmin(u);

        if (isValidUser) {
            // Create a session and store the username
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to the welcome page
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Login successful!');");
            out.println("window.location.href = 'index.jsp';");
            out.println("</script>");
        } else if (isValidAdmin) {
            // Create a session and store the username
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Redirect to the admin dashboard
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Login successful! Welcome admin.');");
            out.println("window.location.href = 'CourierList';");
            out.println("</script>");
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid username or password. Please try again.');");
            out.println("window.location.href = 'login.jsp';");
            out.println("</script>");
        }
    }
}
