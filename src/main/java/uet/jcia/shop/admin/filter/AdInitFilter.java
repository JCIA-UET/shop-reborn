package uet.jcia.shop.admin.filter;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uet.jcia.shop.model.Account;
import uet.jcia.shop.model.AccountType;
import uet.jcia.shop.model.CategoryManager;
import uet.jcia.shop.model.Item;

/**
 * Servlet Filter implementation class AdInitFilter
 */
@WebFilter("/admin/*")
public class AdInitFilter implements Filter {
	public AdInitFilter() {}

	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		
		if (account == null ||
			!(account.getAccountType() == AccountType.EMPLOYEE)) {
			ServletContext context = req.getServletContext();
			rsp.sendRedirect(context.getContextPath() + "/login.jsp");
			return ;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
