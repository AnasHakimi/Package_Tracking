package tracking.controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tracking.dao.CourierDAO;
import tracking.model.Courier;

@WebServlet("/CourierList")
public class ListAllCourierController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourierDAO courierDao = new CourierDAO();
        List<Courier> couriers = courierDao.getAllCouriers();

        request.setAttribute("couriers", couriers);
        request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
    }
}
