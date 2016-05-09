package uet.jcia.shop.admin.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import uet.jcia.shop.controller.MessageType;

/**
 * Servlet implementation class UploadService
 */
@WebServlet("/admin/UploadService")
@MultipartConfig(
		fileSizeThreshold = 2 * 1024 * 1024,
		maxFileSize = 10 * 1024 * 1024,
		maxRequestSize = 50 * 1024 * 1024)
public class UploadService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String productDataPath;
	
    public UploadService() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	ServletContext ctx = getServletContext();
    	productDataPath = ctx.getContextPath() + File.separator + 
    			"img" + File.separator +
    			"product" + File.separator;
    	
    	File file = new File(productDataPath);
    	if(!file.exists()) file.mkdirs();
    			 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = null;
		
		try {
			for (Part p : request.getParts()) {
				String fn = getFileName(p);
				if (!fn.isEmpty()) {
					fileName = fn;
				}
				p.write(productDataPath + fn);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(productDataPath);
		request.setAttribute("MessageType", MessageType.SUCCESS);
		request.setAttribute("message", "upload successfully");
		request.setAttribute("fileName", fileName);
		
		String requestFrom = request.getParameter("reqsrc");
		if (requestFrom != null) {
			forwardStream(request, response, requestFrom);
			
		} else {
			forwardStream(request, response, "/admin/upload-result.jsp");
		}
	}
	
	private String getFileName(Part p) {
		String contentDisp = p.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		
		return "";
	}

	private void forwardStream(HttpServletRequest req, HttpServletResponse rsp, String destination)
			throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(destination);
		dispatcher.forward(req, rsp);
	}
}
