package uet.jcia.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		
		
		request.getRequestDispatcher("register.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		String message = null ; 
		String destination = null ;
		HttpSession session = request.getSession();
		
		if(action.equals("login")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Account account = accountManager.authenticate(username, password);
			if(account!=null){
				
				List<Product> cart = new ArrayList<>();
				session.setAttribute("cart", cart);
				session.setAttribute("account", account);
				destination = "/home.jsp";
			}
			else {
			    message = "<script type='text/javascript'>document.getElementById('alert-fail').style.display = 'block';</script>";
				request.setAttribute("message", message);
				destination = "/login.jsp";
			}
		}
		else if(action.equals("register")){
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
					message = "Register Successfull !! ";
					request.setAttribute("message", message);
					destination = "/register-result.jsp";
				}
				else {
					message = "Register fail !!";
					request.setAttribute("message", message);
					destination = "/register-result.jsp";
				}
			}
			else {
				message = "existed";
				request.setAttribute("message", message);
				destination = "/register.jsp";
			}
			
		}
		else if(action.equals("logout")){
			session.removeAttribute("account");
			session.removeAttribute("cart");
			destination ="/home.jsp";
		}
		
		request.getRequestDispatcher(destination).include(request, response);
		
	}

}
