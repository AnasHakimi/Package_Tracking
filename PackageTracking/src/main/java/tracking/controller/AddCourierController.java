package tracking.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import tracking.dao.CourierDAO;
import tracking.model.Courier;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

/**
 * Servlet implementation class AddCourierController
 */
@WebServlet("/AddCourier")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddCourierController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourierDAO dao;

    public AddCourierController() {
        super();
        dao = new CourierDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Courier nc = new Courier();

        nc.setCourier_name(request.getParameter("courier_name"));
        nc.setCourier_availability(request.getParameter("courier_availability"));

        // Handle file upload
        Part filePart = request.getPart("courier_logo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create the images directory if it does not exist
        }
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        nc.setCourier_logo("images/" + fileName); // Set relative path to save in DB

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        boolean isAdded = dao.addCourierInfo(username, nc);

        if (isAdded) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Successful Add Courier!');");
            out.println("window.location.href = 'CourierList';");
            out.println("</script>");
        } else {
            request.setAttribute("errorMessage", "Failed. Please try again.");
            RequestDispatcher view = request.getRequestDispatcher("addCourier.jsp");
            view.forward(request, response);
        }
    }
}


