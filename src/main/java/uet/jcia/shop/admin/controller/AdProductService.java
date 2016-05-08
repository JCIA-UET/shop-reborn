package uet.jcia.shop.admin.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uet.jcia.shop.controller.MessageType;
import uet.jcia.shop.model.Category;
import uet.jcia.shop.model.CategoryManager;
import uet.jcia.shop.model.Item;
import uet.jcia.shop.model.Product;
import uet.jcia.shop.model.ProductManager;

/**
 * Servlet implementation class AdProductService
 */
@WebServlet("/admin/AdProductService")
public class AdProductService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductManager pm;
	private CategoryManager cm;
	
    public AdProductService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	pm = new ProductManager();
    	cm = new CategoryManager();
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
			List<Product> productsList = (List<Product>)(List<?>) pm.getAllItems();
			
			if (productsList == null) {
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "Error when get products list");
				
			} else {
				for (Product p : productsList) {
					String catName = cm.getNameById(p.getCategoryId());
					p.setCategoryName(catName);
				}
				
				req.setAttribute("productsList", productsList);
			}
			
			destination = "/admin/products-list.jsp";
		}
		// get product by id
		else if (action.equalsIgnoreCase("gpbyid")) {
			String pIdStr = req.getParameter("productid");
			int pId = Integer.parseInt(pIdStr);
			Product p = (Product) pm.getItemById(pId);
			List<Item> categories = cm.getAllItems();
			
			req.setAttribute("product", p);
			req.setAttribute("categories", categories);
			destination = "/admin/update-product.jsp";
		}
		// request to go the add product page
		else if (action.equalsIgnoreCase("req2addp")) {
			List<Item> categories = cm.getAllItems();
			req.setAttribute("categories", categories);
			destination = "/admin/new-product.jsp";
		}

		forwardStream(req, rsp, destination);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if (action == null) {
			return ;
		}
			
		String destination = null;
		
		// haven't optimized yet
		if (action.equalsIgnoreCase("addproduct")) {
			String name = req.getParameter("name");
			String catIdStr = req.getParameter("categoryid");
			String priceStr = req.getParameter("price");
			String qttStr = req.getParameter("quantity");
			String imgLink = req.getParameter("imagelink");
			String description = req.getParameter("description");
			
			destination = "/admin/new-product.jsp";
			
			if (name == null || catIdStr == null ||
				priceStr == null || qttStr == null ||
				description == null) {
				
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "You cannot leave any field empty");
				
			} else {
				int categoryId = Integer.parseInt(catIdStr);
				double price = Double.parseDouble(priceStr);
				int quantity =  Integer.parseInt(qttStr);
				
				Product p = new Product(name, quantity, price, categoryId, description, imgLink);
				int result = pm.addItem(p);
				
				if (result == 0) {
					req.setAttribute("messageType", MessageType.ERROR);
					req.setAttribute("message", "Error when add product");
					
				} else {
					String messageType = MessageType.SUCCESS.name();
					String message = "Add product successfully";
					destination = "AdProductService";
					
					rsp.sendRedirect(String.format("%s?action=gallp&messagetype=%s&message=%s",
							destination,
							URLEncoder.encode(messageType, "UTF-8"),
							URLEncoder.encode(message, "UTF-8")));
					return ;
				}
			}
			
		} else if (action.equalsIgnoreCase("updateproduct")) {
			String productIdStr = req.getParameter("productid");
			String name = req.getParameter("name");
			String catIdStr = req.getParameter("categoryid");
			String priceStr = req.getParameter("price");
			String qttStr = req.getParameter("quantity");
			String imgLink = req.getParameter("imagelink");
			String description = req.getParameter("description");
			destination = "/admin/update-product.jsp";
			
			if (name == null || catIdStr == null ||
				priceStr == null || qttStr == null ||
				description == null) {
				
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "You cannot leave any field empty");
				
			} else {
				int productId = Integer.parseInt(productIdStr);
				int categoryId = Integer.parseInt(catIdStr);
				double price = Double.parseDouble(priceStr);
				int quantity =  Integer.parseInt(qttStr);
				
				imgLink = "hihiimg";
				Product p = new Product(productId, name, quantity, price, categoryId, description, imgLink);
				
				boolean result = pm.updateItem(productId, p);
				if (!result) {
					req.setAttribute("messageType", MessageType.ERROR);
					req.setAttribute("message", "Error when update product");
					
				} else {
					String messageType = MessageType.SUCCESS.name();
					String message = "Update category successfully";
					
					destination = "AdProductService";
					rsp.sendRedirect(String.format("%s?action=gallp&messagetype=%s&message=%s",
							destination,
							URLEncoder.encode(messageType, "UTF-8"),
							URLEncoder.encode(message, "UTF-8")));
					return ;
				}
			
			}
		} else if (action.equalsIgnoreCase("removeproduct")) {
			String catIdStr = req.getParameter("productid");
			int catId = Integer.parseInt(catIdStr);
			
			boolean result = pm.removeItemById(catId);
			String messageType;
			String message;
			
			if (!result) {
				messageType = MessageType.ERROR.name();
				message = "Error when remove product";
				
			} else {
				messageType = MessageType.SUCCESS.name();
				message = "Remove product successfully";
			}
			
			destination = "AdProductService";
			rsp.sendRedirect(String.format("%s?action=gallp&messagetype=%s&message=%s",
					destination,
					URLEncoder.encode(messageType, "UTF-8"),
					URLEncoder.encode(message, "UTF-8")));
			return ;
		}
		
		forwardStream(req, rsp, destination);
	}

	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
