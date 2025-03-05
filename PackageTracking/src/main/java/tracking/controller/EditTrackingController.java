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
 * Servlet implementation class EditTrackingController
 */
@WebServlet("/EditTracking")
public class EditTrackingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrackingDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTrackingController() {
        super();
        dao = new TrackingDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("itemID"));
		request.setAttribute("s", TrackingDAO.getTrackingById(id));
		RequestDispatcher view = request.getRequestDispatcher("updateTracking.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   Shipment s = new Shipment();
	   s.setItemID(Integer.parseInt(request.getParameter("id")));
	   s.setTracking_num(request.getParameter("tracking_num"));
	   s.setTitle(request.getParameter("title"));
	   s.setCourier_name(request.getParameter("courier"));
	   
	   dao.updateTracking(s);
	   
	   HttpSession session = request.getSession();
       String username = (String) session.getAttribute("username");

       if (username != null) {
           List<Shipment> shipments = dao.getAllShipments(username);
           request.setAttribute("shipments", shipments);
       } else {
           request.setAttribute("shipments", new ArrayList<Shipment>());
       }

       
       UserDAO userDao = new UserDAO();
       User user = userDao.getUserDetails(username);
       request.setAttribute("user", user);
       
       session.setAttribute("editSuccess", true);

       RequestDispatcher view = request.getRequestDispatcher("profile.jsp");
       view.forward(request, response);
	   
	   
	   
	}

}
