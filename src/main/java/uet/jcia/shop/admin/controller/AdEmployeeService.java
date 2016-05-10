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

import uet.jcia.shop.controller.MessageType;
import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.AccountType;

/**
 * Servlet implementation class AdEmployeeService
 */
@WebServlet("/admin/AdEmployeeService")
public class AdEmployeeService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountManager am;
    
    public AdEmployeeService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	am = new AccountManager();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			return ;
		}
		// Get ALL Employees
		if(action.equalsIgnoreCase("galle")){
			List<Account> listAccount = am.getAllAccounts(AccountType.EMPLOYEE);
			request.setAttribute("employees", listAccount);
			forwardStream(request, response, "/admin/employees-list.jsp");
		}
		// Get Employee BY ID
		else if(action.equalsIgnoreCase("gebyid")){
				String idStr = request.getParameter("eid");
				int id = Integer.parseInt(idStr);
				
				Account account = am.getAccountById(id);
				request.setAttribute("employee", account);
				forwardStream(request, response, "/admin/update-employee.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			return ;
		}
		
		MessageType messageType = null;
		String message = null;
		String destination = null;
		
		if (action.equalsIgnoreCase("remove")) {
			int id = Integer.parseInt(request.getParameter("eid"));
			boolean res = am.removeAccountById(id);
			if (res) {
				messageType = MessageType.SUCCESS;
				message = "Remove successfully";
			} else {
				messageType = MessageType.ERROR;
				message = "An error occured while removing";
			}
			destination = "/admin/employees-list.jsp";
			
		} else if (action.equalsIgnoreCase("add")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realName = request.getParameter("realname");
			String phonenumber = request.getParameter("phonenumber");
			String city = request.getParameter("city");
			String address = request.getParameter("address");
			String role = request.getParameter("role");
			Account acc = new Account(username, password, realName, phonenumber, city, address, AccountType.valueOf(role));
			
			if (am.addAccount(acc) > 0) {
				messageType = MessageType.SUCCESS;
				message = "Add successfully";
				destination = "/admin/employees-list.jsp";
			} else {
				messageType = MessageType.ERROR;
				message = "An error occured while adding new employee";
				destination = "/admin/new-employee.jsp";
			}
			
		} else if (action.equals("update")) {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realName = request.getParameter("realname");
			String phonenumber = request.getParameter("phone");
			String city = request.getParameter("city");
			String address = request.getParameter("address");
			String role = request.getParameter("role");
			
			Account acc = new Account(id, username, password, realName, phonenumber, city, address, AccountType.valueOf(role));
			
			if (am.updateEmployee(id, acc)) {
				messageType = MessageType.SUCCESS;
				message = "Update successfully";
				destination = "/admin/employees-list.jsp";
			} else {
				messageType = MessageType.ERROR;
				message = "An error occured while updating employee";
				destination = "/admin/update-employee.jsp";
			}
		}
		
		request.setAttribute("messageType", messageType);
		request.setAttribute("message", message);
		forwardStream(request, response, destination);
	}
	
	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
