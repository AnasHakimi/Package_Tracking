package tracking.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tracking.dao.CourierDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CourierServlet
 */
@WebServlet("/couriers")
public class ListCourierController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourierDAO courierDAO;

    @Override
    public void init() {
        courierDAO = new CourierDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> couriers = courierDAO.getAllAvailableCouriers();
        StringBuilder result = new StringBuilder();
        for (String courier : couriers) {
            result.append(courier).append("\n");
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result.toString());
    }
}