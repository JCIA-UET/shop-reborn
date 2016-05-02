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
import uet.jcia.shop.model.AccountType;
import uet.jcia.shop.model.Product;
import uet.jcia.shop.model.ProductManager;

/**
 * Servlet implementation class TransactionService
 */
@WebServlet("/TransactionService")
public class TransactionService extends HttpServlet {
	private ProductManager productManager;
	private static final long serialVersionUID = 1L;
	
	@Override
    public void init() throws ServletException {
    	super.init();
    	productManager = new ProductManager();
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
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
		
		System.out.println("Action:" +action);
		
		if (action == null) {
			return ;
		}
		
		if (action.equalsIgnoreCase("show-cart")) {
			//List<Product> shoppingCart = (List<Product>) session.getAttribute("cart");
			session.setAttribute("cart", shoppingCart);
			destination = "/checkout.jsp";
			forwardStream(request, response, destination);
		} 
		else if (action.equalsIgnoreCase("add2cart")) {
			destination = "/shop/index.jsp";
			
			String szPId = request.getParameter("productid");
			int pId = Integer.parseInt(szPId);
			
			String szQuantity = request.getParameter("qtt");
			int quantity = Integer.parseInt(szQuantity);
			
			Product product = (Product) productManager.getItemById(pId);
			
			System.out.println(product);
			if (product != null) {
				shoppingCart.add(product);
				request.setAttribute("messageType", MessageType.SUCCESS);
				message = product.getName() + " (x" + product.getQuantity() + ") has been added to cart successfully.";
				session.setAttribute("cart", shoppingCart);
				request.setAttribute("message", message);
				request.setAttribute("chosenProduct", product);
			} /*else {
				message = "Errors occurs when adding product to your cart.";
				request.setAttribute("messageType", MessageType.ERROR);
				request.setAttribute("message", message);
			}*/
			System.out.println(message);
			
			for (Product p : shoppingCart) {
				System.out.println("In cart" + p);
			}
			response.sendRedirect(destination);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
