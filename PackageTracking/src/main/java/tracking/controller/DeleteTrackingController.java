package tracking.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tracking.dao.TrackingDAO;

import java.io.IOException;

/**
 * Servlet implementation class DeleteTrackingController
 */
@WebServlet("/DeleteTracking")
public class DeleteTrackingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TrackingDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTrackingController() {
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
		dao.deleteTracking(id);
		response.sendRedirect("ListShipments");
	}

}
