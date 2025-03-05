package tracking.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tracking.dao.TrackingDAO;
import tracking.dao.UserDAO;
import tracking.model.Shipment;
import tracking.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class ListShipmentsServlet
 */
@WebServlet("/ListShipments")
public class ListTrackingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TrackingDAO trackDAO;
    

    public ListTrackingController() {
        super();
        trackDAO = new TrackingDAO();
      
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            List<Shipment> shipments = trackDAO.getAllShipments(username);
            request.setAttribute("shipments", shipments);
        } else {
            request.setAttribute("shipments", new ArrayList<Shipment>());
        }

        
        UserDAO userDao = new UserDAO();
        User user = userDao.getUserDetails(username);
        request.setAttribute("user", user);
        
        RequestDispatcher view = request.getRequestDispatcher("profile.jsp");
        view.forward(request, response);
    }
}
