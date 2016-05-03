package uet.jcia.shop.controller;

import java.io.IOException;
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

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountManager;
import uet.jcia.shop.model.AccountType;
import uet.jcia.shop.model.Product;
import uet.jcia.shop.model.ProductManager;
import uet.jcia.shop.model.Transaction;

/**
 * Servlet implementation class TransactionService
 */
@WebServlet("/TransactionService")
public class TransactionService extends HttpServlet {
	private ProductManager productManager;
	private Transaction transaction;
	private static final long serialVersionUID = 1L;
	
	@Override
    public void init() throws ServletException {
    	super.init();
    	productManager = new ProductManager();
    	transaction = new Transaction();
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String message = null;
		String destination = null;
		
		HttpSession session = request.getSession();
		List<Product> shoppingCart = (List<Product>) session.getAttribute("cart");
		if(shoppingCart == null) {
			shoppingCart = new ArrayList<Product>();
		}
		
		if (action.equalsIgnoreCase("add2cart")) {
			destination = "/shop/index.jsp";
			boolean result = false;
			
			String szPId = request.getParameter("productid");
			int pId = Integer.parseInt(szPId);
			
			String szQuantity = request.getParameter("qtt");
			int quantity = Integer.parseInt(szQuantity);
			
			Product product = (Product) productManager.getItemById(pId);

			if (product != null) {
				Product pFound = null;
				// Check if item is exist in cart or not???
				pFound = (Product) transaction.findItemById(shoppingCart, pId);
				
				// Nothing, add to cart
				if(pFound == null) {
					product.setQuantity(quantity);
					shoppingCart.add(product);
					result = true;
				}
				else {
					result = false;
				}
			}
			else {
				result = false;
			}
				
			System.out.println(message);

			if(!result) {
				double totalCash = transaction.getTotalCash(shoppingCart);
				message = "Error when adding product in cart or product is exist in cart.";
				request.setAttribute("messageType", MessageType.ERROR);
				request.setAttribute("message", message);
				session.setAttribute("cart", shoppingCart);
				session.setAttribute("totalcash", totalCash);
			}
			else {
				double totalCash = transaction.getTotalCash(shoppingCart);
				message = "Add successfully";
				request.setAttribute("messageType", MessageType.SUCCESS);
				request.setAttribute("message", message);
				session.setAttribute("cart", shoppingCart);
				session.setAttribute("totalcash", totalCash);
			}
			
			double totalCash = transaction.getTotalCash(shoppingCart);
			session.setAttribute("totalcash", totalCash);
			response.sendRedirect(destination);
		}
		else if (action.equalsIgnoreCase("delete")) {
			destination = "your-cart.jsp";
			String szPId = request.getParameter("productid");
			int pId = Integer.parseInt(szPId);
			
			boolean result = transaction.removeItemById(shoppingCart, pId);
			
			if(!result) {
				message = "Error when deleting product in cart";
				request.setAttribute("messageType", MessageType.ERROR);
				request.setAttribute("message", message);
				session.setAttribute("cart", shoppingCart);
			}
			else {
				message = "Delete successfully";
				request.setAttribute("messageType", MessageType.SUCCESS);
				request.setAttribute("message", message);
				session.setAttribute("cart", shoppingCart);
			}
			
			double totalCash = transaction.getTotalCash(shoppingCart);
			session.setAttribute("totalcash", totalCash);
			response.sendRedirect(destination);
			
			return;
		}
		else if(action.equalsIgnoreCase("update")) {
			destination = "your-cart.jsp";
			String szPId = request.getParameter("productid");
			int pId = Integer.parseInt(szPId);
			
			String szQuantity = request.getParameter("qtt");
			int quantity = Integer.parseInt(szQuantity);
			System.out.println("new:" + quantity);
			
			boolean result = transaction.changeQttOfProduct(shoppingCart, pId, quantity);
			
			if(!result) {
				message = "Error when updating quantity";
				request.setAttribute("messageType", MessageType.ERROR);
				request.setAttribute("message", message);
				session.setAttribute("cart", shoppingCart);
			}
			else {
				message = "Update successfully";
				request.setAttribute("messageType", MessageType.SUCCESS);
				request.setAttribute("message", message);
				session.setAttribute("cart", shoppingCart);
			}
			
			double totalCash = transaction.getTotalCash(shoppingCart);
			session.setAttribute("totalcash", totalCash);
			response.sendRedirect(destination);
		}
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		
		String message = null;
		String destination = null;
		
		HttpSession session = request.getSession();
		List<Product> shoppingCart = (List<Product>) session.getAttribute("cart");
		
		if(shoppingCart == null) {
			shoppingCart = new ArrayList<Product>();
		}
		
		
		if(action.equalsIgnoreCase("checkout")) {
			System.out.println("CHECKOUT");
			destination = "/checkout.jsp";

			Account acc = (Account) session.getAttribute("account");
			
			if(acc == null) {
				destination = "/login.jsp";
				forwardStream(request, response, destination);
				return;
			}
			
			System.out.println(acc.getId() + acc.getUsername() + acc.getAccountType().toString());
			
			AccountType type = acc.getAccountType();
			if(type != AccountType.CUSTOMER) return;
			
			if(!shoppingCart.isEmpty()){
				transaction.doBuy(null, acc.getAddress(), acc, shoppingCart);
			
				request.setAttribute("messageType", MessageType.SUCCESS);
				request.setAttribute("buyResult", true);
				request.setAttribute("message", "All your items are delivered to your address!");
			
				session.removeAttribute("cart");
				session.removeAttribute("totalcash");
			}
			else {
				request.setAttribute("messageType", MessageType.ERROR);
				request.setAttribute("message", "You have no item in cart.");
			}

			forwardStream(request, response, destination);
			return;
			
		}
	}

	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
