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

import uet.jcia.shop.model.CategoryManager;
import uet.jcia.shop.model.Item;

/**
 * Servlet implementation class CategoryService
 */
@WebServlet("/CategoryService")
public class CategoryService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryManager cm;
    
    public CategoryService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	cm = new CategoryManager();
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
