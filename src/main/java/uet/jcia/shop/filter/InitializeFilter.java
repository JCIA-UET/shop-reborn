package uet.jcia.shop.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uet.jcia.shop.model.CategoryManager;
import uet.jcia.shop.model.Item;

/**
 * Servlet Filter implementation class InitializeFilter
 */
@WebFilter("/*")
public class InitializeFilter implements Filter {
	
    public InitializeFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		List<Item> categories = (List<Item>) session.getAttribute("ss_categories");
		
		if (categories == null) {
			CategoryManager cm = new CategoryManager();
			List<Item> categories2 = cm.getAllItems();
			
			categories = new ArrayList<>();
			categories.addAll(categories2);
			session.setAttribute("ss_categories", categories);
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
