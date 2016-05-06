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
		// get category by id
		else if (action.equalsIgnoreCase("gcbyid")) {
			String catIdStr = req.getParameter("categoryid");
			int catId = Integer.parseInt(catIdStr);
			Category c = (Category) cm.getItemById(catId);
			
			req.setAttribute("category", c);
			destination = "/admin/update-category.jsp";
		}
		
		forwardStream(req, rsp, destination);
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
			
		if (action == null) {
			return ;
		}
			
		String destination = null;
		
		// haven't optimized yet
		if (action.equalsIgnoreCase("addcat")) {
			String name = req.getParameter("name");
			String des = req.getParameter("description");
			destination = "/admin/new-category.jsp";
			
			if (name == null || des == null) {
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "You cannot leave any field empty");
				
			} else {
				int result = cm.addItem(new Category(name, des));
				
				if (result == 0) {
					req.setAttribute("messageType", MessageType.ERROR);
					req.setAttribute("message", "Error when add category");
					
				} else {
					String messageType = MessageType.SUCCESS.name();
					String message = "Add category successfully";
					destination = "AdCategoryService";
					
					rsp.sendRedirect(String.format("%s?action=gallc&messagetype=%s&message=%s",
							destination,
							URLEncoder.encode(messageType, "UTF-8"),
							URLEncoder.encode(message, "UTF-8")));
					return ;
				}
			}
			
		} else if (action.equalsIgnoreCase("updatecat")) {
			String name = req.getParameter("name");
			String des = req.getParameter("description");
			String catIdStr = req.getParameter("categoryid");
			int catId = Integer.parseInt(catIdStr);
			destination = "/admin/update-category.jsp";
			
			if (name == null || des == null) {
				req.setAttribute("messageType", MessageType.ERROR);
				req.setAttribute("message", "You cannot leave any field empty");
			
			} else {
				boolean result = cm.updateItem(catId, new Category(catId, name, des));
				if (!result) {
					req.setAttribute("messageType", MessageType.ERROR);
					req.setAttribute("message", "Error when update category");
					
				} else {
					String messageType = MessageType.SUCCESS.name();
					String message = "Update category successfully";
					
					destination = "AdCategoryService";
					rsp.sendRedirect(String.format("%s?action=gallc&messagetype=%s&message=%s",
							destination,
							URLEncoder.encode(messageType, "UTF-8"),
							URLEncoder.encode(message, "UTF-8")));
					return ;
				}
			
			}
		} else if (action.equalsIgnoreCase("removecat")) {
			String catIdStr = req.getParameter("categoryid");
			int catId = Integer.parseInt(catIdStr);
			
			boolean result = cm.removeItemById(catId);
			String messageType;
			String message;
			
			if (!result) {
				messageType = MessageType.ERROR.name();
				message = "Error when remove category";
				
			} else {
				messageType = MessageType.SUCCESS.name();
				message = "Remove category successfully";
			}
			
			destination = "AdCategoryService";
			rsp.sendRedirect(String.format("%s?action=gallc&messagetype=%s&message=%s",
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
