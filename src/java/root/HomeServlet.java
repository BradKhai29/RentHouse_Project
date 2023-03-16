package controller.root;

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
import support_enum.*;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;

@WebServlet(name = "HomeServlet", displayName = "HomeServlet", urlPatterns = { "/HomeServlet", "/home" })
public final class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final URLBuilder URL_BUILDER = URLBuilderFactory.get();
	private static final String homeURL;
	
	static {
		homeURL = URL_BUILDER.addPage(WebPageEnum.HOME).getURL();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitRootURLs(request);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(homeURL);
		requestDispatcher.forward(request, response);
	}
	
	private void InitRootURLs(HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		String rootURL = application.getContextPath();
		
		//Set some URLs
		application.setAttribute(AttributeEnum.ROOT.name().toLowerCase(), rootURL);
	}
}
