package tracking.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tracking.dao.TrackingDAO;
import tracking.model.Shipment;

/**
 * Servlet implementation class AddTrackingServlet
 */
@WebServlet("/AddTracking")
public class AddTrackingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TrackingDAO trackDAO;

    public AddTrackingController() {
        super();
        trackDAO = new TrackingDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
    	Shipment ns = new Shipment();
    	
    	ns.setTracking_num(request.getParameter("tracking_num"));
    	ns.setTitle(request.getParameter("title"));
    	ns.setCourier_name(request.getParameter("courier"));
    	
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        
        if (username == null) {
            response.sendRedirect("login.jsp"); 
            return;
        }

       
        boolean isAdded = trackDAO.addTrackingInfo(username, ns);

        if (isAdded) {
            response.sendRedirect("viewStatus.jsp?tracking_num=" + ns.getTracking_num()); 
        } else {
            response.sendRedirect("index.jsp"); 
        }
    }
}
