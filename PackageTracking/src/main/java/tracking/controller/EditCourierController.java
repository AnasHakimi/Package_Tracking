package tracking.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tracking.dao.CourierDAO;
import tracking.model.Courier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Servlet implementation class EditCourierController
 */
@WebServlet("/EditCourier")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EditCourierController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourierDAO dao;

    public EditCourierController() {
        super();
        dao = new CourierDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("c", CourierDAO.getCourierById(id));
        RequestDispatcher view = request.getRequestDispatcher("updateCourier.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Courier c = new Courier();
        c.setCourier_id(Integer.parseInt(request.getParameter("courier_id")));
        c.setCourier_name(request.getParameter("courier_name"));
        c.setCourier_availability(request.getParameter("courier_availability"));

        // Handle file upload
        Part filePart = request.getPart("courier_logo");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (!fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // Create the images directory if it does not exist
            }
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            c.setCourier_logo("images/" + fileName); // Set relative path to save in DB
        } else {
            // If no new file is uploaded, keep the existing logo
            c.setCourier_logo(request.getParameter("current_courier_logo"));
        }

        dao.updateCourier(c);

        List<Courier> couriers = dao.getAllCouriers();
        request.setAttribute("couriers", couriers);
    	request.getSession().setAttribute("editSuccess", true);

        RequestDispatcher view = request.getRequestDispatcher("admindashboard.jsp");
        view.forward(request, response);
    }
}
