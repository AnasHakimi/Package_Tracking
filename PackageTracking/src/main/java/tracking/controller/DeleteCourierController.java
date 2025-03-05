package tracking.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tracking.dao.CourierDAO;

import java.io.IOException;

/**
 * Servlet implementation class DeleteCourierController
 */
@WebServlet("/DeleteCourier")
public class DeleteCourierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourierDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCourierController() {
        super();
        dao = new CourierDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deleteCourier(id);
		response.sendRedirect("CourierList");
	}

	

}
