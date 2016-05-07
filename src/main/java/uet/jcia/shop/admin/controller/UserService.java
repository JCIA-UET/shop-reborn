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

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;

/**
 * Servlet implementation class UserService
 */
@WebServlet("/admin/UserService")
public class UserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountManager accountManager = new AccountManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			return ;
		}
		
		if(action.equalsIgnoreCase("gallcus")){
			List<Account> listAccount = accountManager.getAllCustomer();
			request.setAttribute("listAccount", listAccount);
			forwardStream(request, response, "/admin/users.jsp");
		}
		else if(action.equalsIgnoreCase("gusbid")){
				String id = request.getParameter("userid");
				int id1 = Integer.parseInt(id);
				Account account = accountManager.getAccountById(id1);
				request.setAttribute("account", account);
				forwardStream(request, response, "/admin/update-user.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String message;
		String destination = null;
		if(action==null) return;
		if(action.equalsIgnoreCase("update-user")){
			String idString = request.getParameter("userId");
			int id = Integer.parseInt(idString);
			Account account = accountManager.getAccountById(id);
			String realName = request.getParameter("realname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			account.setRealName(realName);
			account.setPhone(phone);
			account.setAddress(address);
			account.setCity(city);
			accountManager.updateAccount(id, account);
			request.setAttribute("account", account);
			destination = "/admin/update-user.jsp";
		}
		else if(action.equalsIgnoreCase("removeUser")){
			String idString = request.getParameter("userid");
			int id = Integer.parseInt(idString);
			boolean remove = accountManager.removeAccountById(id);
			if(remove){
				message = "remove Successfull!!";
				destination="/admin/users.jsp";
			}
			else{
				message ="Sorry, remove fail !!";
				destination = "/admin/users.jsp";
			}
		}
		if(destination!=null){
			forwardStream(request, response, destination);
		}
	}
	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}

}
