package controller.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.cookie.CookieSupportServlet;
import support_enum.ActionEnum;
import entity.user.User;
import entity.user.UserDAO;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;
import support_enum.*;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({ "/UserServlet", "/user" })
public final class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final URLBuilder URL_BUILDER = URLBuilderFactory.get();
	
	private static String HOMEPAGE = URL_BUILDER.addPage(WebPageEnum.HOME).getURL();
	private static String LOGIN_PAGE = URL_BUILDER
										.addFolder(FolderEnum.USER)
										.addPage(WebPageEnum.USER_LOGIN)
										.getURL();
	private static String REGISTER_PAGE = URL_BUILDER
										.addFolder(FolderEnum.USER)
										.addPage(WebPageEnum.USER_REGISTER)
										.getURL();
	
	private static final UserDAO userDAO;

    static {
        userDAO = new UserDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String receiveCommand = request.getParameter("command");
		ActionEnum command = ActionEnum.get(receiveCommand);
		System.out.println(command);
		
		RequestDispatcher requestDispatcher;
		switch (command) {
		case LOGIN:
			processLogIn(request, response);
			break;
		case LOGOUT:
			processLogOut(request, response);
			break;
		case REGISTER:
			requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE);
			requestDispatcher.forward(request, response);
			break;
		default:
			requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
			requestDispatcher.forward(request, response);
		}
	}
	
	private void processLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		
		CookieEnum cookieEnum = CookieEnum.REMEMBER_USER;
		String rememeberCookieValue = CookieSupportServlet.processCookie(request, response, cookieEnum, true);
		RememberUserManager.remove(rememeberCookieValue);
		
		request.getRequestDispatcher(HOMEPAGE).forward(request, response);
	}
	
	private void processLogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Served at [" + getServletName() + "]");
        request.setCharacterEncoding(webpage_tools.PrintTools.getUTF8());
        HttpSession session = request.getSession(true);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Optional<User> user = userDAO.Authenticate(username, password);
        boolean doRememeber = remember != null;

        RequestDispatcher requestDispatcher;
        if (user.isPresent()) 
        {
            if (doRememeber) 
            {
                createRememberUserCookie(response, user.get());
            }
            session.setAttribute(AttributeEnum.USER.name(), user.get());
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
        } 
        else 
        {
            ErrorEnum message = ErrorEnum.LOGIN_ERROR;
            session.setAttribute(message.name(), message.getMessage());
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(request, response);
        }
	}
	
	private void createRememberUserCookie(HttpServletResponse response, User user) 
    {
        String userHashCode = Integer.toString(user.hashCode());
        CookieSupportServlet.addCookie(response, CookieEnum.REMEMBER_USER, userHashCode);
        //Add this user to rememeber maps for later auto-login function with rememberUserCookie
        if (RememberUserManager.get(userHashCode).isEmpty())
        {
            RememberUserManager.add(userHashCode, user);
        }
    }

}
