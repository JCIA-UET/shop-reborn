package uet.jcia.shop.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uet.jcia.shop.model.*;

import uet.jcia.shop.model.OrderManager;

/**
 * Servlet implementation class StatisService
 */
@WebServlet("/admin/StatisService")
public class StatisService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    OrderManager orderManager = new OrderManager();
    private AccountManager am;
    
    public StatisService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	am = new AccountManager();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) return ;
		String destination = null ;
		
		// gts = get top selling
		if(action.equalsIgnoreCase("gts")){
			String numberString = request.getParameter("number");
			int number = Integer.parseInt(numberString);
			List<Item> list =  orderManager.getTopSellingProduct(number);
			if(list == null) {
				System.out.println("null");
				return;
			}
			request.setAttribute("listTop", list);
			destination = "/admin/statis-product.jsp";
		}
		// Get Top Customers
		else if (action.equalsIgnoreCase("gtc")) {
			String n = request.getParameter("threshold");
			int threshold = Integer.parseInt(n);
			List<Account> customers = orderManager.getTopCustomers(threshold);
			for (int i = 0; i < customers.size(); i++) {
				Account c = customers.get(i);
				int id = c.getId();
				Account temp = am.getAccountById(id);
				c.setRealName(temp.getRealName());
			}
			
			request.setAttribute("customers", customers);
			destination = "/admin/top-customers.jsp";
		}
		
		forwardStream(request, response, destination);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
