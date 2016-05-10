package uet.jcia.shop.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uet.jcia.shop.model.*;

/**
 * Servlet implementation class StatisService
 */
@WebServlet("/admin/StatistService")
public class StatistService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    OrderManager orderManager = new OrderManager();
    private AccountManager am;
    
    public StatistService() {
    }
    
    @Override
    public void init() throws ServletException {
    	am = new AccountManager();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String numberString = request.getParameter("number");
		String destination = null;
		String message = null;
		double[] arr = new double[3];
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		
		if(account == null || (account != null && account.getAccountType() != AccountType.EMPLOYEE)) {
			message = "You do not have permission.";
			request.setAttribute("message", message);
			destination = "/admin/error.jsp";
			forwardStream(request, response, destination);
			return;
		}
	
		// gts = get top selling
		else if(action.equalsIgnoreCase("gts")){
			int number = Integer.parseInt(numberString);
			List<Item> list =  orderManager.getTopSellingProduct(number);
			if(list == null) {
				System.out.println("null");
				return;
			}
			request.setAttribute("listTop", list);
			destination = "/admin/statist-product.jsp";
			forwardStream(request, response, destination);
		}
		// Calculate Daily Revenue
		else if(action.equalsIgnoreCase("cdr")) {
			
			String szDay = (String)request.getParameter("day");
			String szMonth = (String)request.getParameter("month");
			String szYear = (String)request.getParameter("year");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String szDate = String.format(szYear + "-" + szMonth + "-" + szDay);
			Date date;
			try {
				date = sdf.parse(szDate);
				arr = orderManager.calDailyRevenue(date);
				request.setAttribute("action", action);
				request.setAttribute("gettopselling", false);
				request.setAttribute("result", arr);
				forwardStream(request, response, "/admin/statist-sale.jsp");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				message = "Date format error";
				request.setAttribute("message", message);
				forwardStream(request, response, "/admin/error.jsp");
			}
		}
		// Caculate monthly revenue
		else if(action.equalsIgnoreCase("cmr")) {
			String szMonth = request.getParameter("month");
			String szYear = request.getParameter("year");
			
			// Convert String to Int
			int month = Integer.parseInt(szMonth);
			
			arr = orderManager.calMonthRevenue(month);
			request.setAttribute("action", action);
			request.setAttribute("result", arr);
			forwardStream(request, response, "/admin/error.jsp");
		}
		// Caculate total revenue
		else if (action.equalsIgnoreCase("ctr")) {
			arr = orderManager.calTotalRevenue();
			request.setAttribute("action", action);
			request.setAttribute("result", arr);
			forwardStream(request, response, "/admin/error.jsp");
		}
		
		
		
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
		
	}
	
	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}

}
