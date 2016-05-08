package uet.jcia.shop.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.OrderManager;

/**
 * Servlet implementation class AdDashboardService
 */
@WebServlet("/admin/")
public class AdDashboardService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderManager om;
    private AccountManager am;
	
    public AdDashboardService() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	om = new OrderManager();
    	am = new AccountManager();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destination = "/admin/dashboard.jsp";
		
		double totalRevenue = om.calTotalRevenue()[2];
		int ordersNum = om.countItems();
		int customersNum = am.countItems();
	
		request.setAttribute("totalRevenue", totalRevenue);
		request.setAttribute("ordersNum", ordersNum);
		request.setAttribute("customersNum", customersNum);
		forwardStream(request, response, destination);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
