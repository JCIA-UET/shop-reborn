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
import uet.jcia.shop.model.CategoryManager;
import uet.jcia.shop.model.Item;

/**
 * Servlet implementation class AdCategoryService
 */
@WebServlet("/admin/AdCategoryService")
public class AdCategoryService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryManager cm;
    
    public AdCategoryService() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	cm = new CategoryManager();
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse rsp)
    		throws ServletException, IOException {
    	String action = req.getParameter("action");
		
		if (action == null) {
			return ;
		}
		
		String destination = null;
		
		// Get ALL Categories
		if (action.equalsIgnoreCase("gallc")) {
			List<Item> categoriesList = cm.getAllItems();
			if (categoriesList == null) {
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "Error when get categories list");
				
			} else {
				req.setAttribute("categoriesList", categoriesList);
			}
			
			destination = "/admin/categories-list.jsp";
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
