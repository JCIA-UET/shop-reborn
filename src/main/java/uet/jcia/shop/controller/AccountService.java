package uet.jcia.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.AccountType;
import uet.jcia.shop.model.Product;

/**
 * Servlet implementation class AccountService
 */
@WebServlet("/AccountService")
public class AccountService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountManager accountManager = new AccountManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		forwardStream(request, response, "/register.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		String message = null ;
		MessageType messageType = null;
		String destination = null ;
		HttpSession session = request.getSession();
		
		if(action.equalsIgnoreCase("login")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Account account = accountManager.authenticate(username, password);
			if(account!=null){
				if(account.getAccountType().equals(AccountType.CUSTOMER)){
					List<Product> cart = (List<Product>) session.getAttribute("cart");
					
					if(cart != null && !cart.isEmpty()) {
						destination = "your-cart.jsp";
					}
					else {
						cart = new ArrayList<Product>();
						destination = "index.jsp";
					}
					
					session.setAttribute("cart", cart);
					session.setAttribute("account", account);
					response.sendRedirect(destination);
					destination = null;
				}
				else{
					destination = "admin/index.jsp";
					response.sendRedirect(destination);
					destination = null;
				}
			}
			else {
				messageType = MessageType.ERROR;
			    message = "Login fail!";
				destination = "/login.jsp";
			}
		}
		else if(action.equalsIgnoreCase("register")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realName = request.getParameter("fullname");
			String phonenumber = request.getParameter("phonenumber");
			String city = request.getParameter("city");
			String address = request.getParameter("address");

			Account accountEx = accountManager.getAccountByUsername(username);
			if(accountEx == null){
				
				Account account = new Account(username, password, realName, phonenumber, city, address, AccountType.CUSTOMER);
					
				int test = accountManager.addAccount(account);
				if(test != 0){
					messageType = MessageType.SUCCESS;
					message = "Register Successfull !! ";
					
				}
				else {
					messageType = MessageType.ERROR;
					message = "Register fail !!";
						
				}
			
				destination = "/login.jsp";
			}
			else {
				messageType = MessageType.ERROR;
				message = "existed";
				destination = "/register.jsp";
			}
		}
		else if(action.equals("logout")){
			destination = "index.jsp";
			session.removeAttribute("account");
			session.removeAttribute("cart");
			ServletContext context = getServletContext();
			response.sendRedirect(destination);
			destination = null ;
		}
		else if (action.equals("changeAccount")){
			destination = "/change-account.jsp";
		}
		else if(action.equals("edit-info")){
		    Account editAcc =(Account) session.getAttribute("account");
		    String name = request.getParameter("fullname");
		    String phonenumber = request.getParameter("phonenumber");
		    String 	city = request.getParameter("city");
		    String address = request.getParameter("address");
		    editAcc.setRealName(name);
		    editAcc.setAddress(address);
		    editAcc.setCity(city);
		    editAcc.setPhone(phonenumber);
		    accountManager.updateAccount(editAcc.getId(), editAcc);
		    session.setAttribute("account", editAcc);
		    destination = "/change-account.jsp";
		    
		}
		else if(action.equals("changePassword")){
			destination = "/change-pass.jsp";
		}
		else if (action.equals("savepass")){
			Account editAcc =(Account) session.getAttribute("account");
			editAcc.setPassword(request.getParameter("newpassword"));
			boolean change = accountManager.updateAccount(editAcc.getId(), editAcc);
			
			if(change){
				messageType = MessageType.SUCCESS;
				message = "change pass successfully!";
			}
			else{
				messageType = MessageType.ERROR;
				message = " sorry, change pass fail!";
			}
			
			destination = "/change-pass.jsp" ;
		}
		
		if(destination != null){
			request.setAttribute("messageType", messageType);
			request.setAttribute("message", message);
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
