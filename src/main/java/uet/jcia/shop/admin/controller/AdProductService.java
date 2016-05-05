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
import uet.jcia.shop.model.Item;
import uet.jcia.shop.model.ProductManager;

/**
 * Servlet implementation class AdProductService
 */
@WebServlet("/admin/AdProductService")
public class AdProductService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductManager pm;
	
    public AdProductService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	pm = new ProductManager();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if (action == null) {
			return ;
		}
		
		String destination = null;
		
		// Get ALL Products
		if (action.equalsIgnoreCase("gallp")) {
			List<Item> productsList = pm.getAllItems();
			if (productsList == null) {
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "Error when get products list");
				
			} else {
				req.setAttribute("productsList", productsList);
			}
			
			destination = "/admin/products-list.jsp";
		}
		
		forwardStream(req, rsp, destination);
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
