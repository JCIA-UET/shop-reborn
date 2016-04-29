package uet.jcia.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uet.jcia.shop.model.Category;
import uet.jcia.shop.model.CategoryManager;
import uet.jcia.shop.model.Item;
import uet.jcia.shop.model.Product;
import uet.jcia.shop.model.ProductManager;

/**
 * Servlet implementation class CategoryService
 */
@WebServlet("/CategoryService")
public class CategoryService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryManager cm;
    private ProductManager pm;
    
    public CategoryService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	cm = new CategoryManager();
    	pm = new ProductManager();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			return ;
		}
		
		String destination = null;
		// Get ALL Categories
		if (action.equalsIgnoreCase("gallc")) {
			List<Item> categories = cm.getAllItems();
			request.setAttribute("categories", categories);
		}
		// Get ALL Category With its Products
		else if (action.equalsIgnoreCase("gallcwp")) {
			List<Category> categories = (List<Category>)(List<?>) cm.getAllItems();
			for (Category c : categories) {
				List<Product> ps = pm.getAllProductByCategoryId(c.getId());
				c.setProducts(ps);
			}
			
			request.setAttribute("categories", categories);
			destination = "/home.jsp";
		}
		
		forwardStream(request, response, destination);
	}
	
	
	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
