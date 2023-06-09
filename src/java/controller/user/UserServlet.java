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
import entity.FactoryDAO.UserDAO;
import java.util.Objects;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;
import support_enum.*;

@WebServlet({"/UserServlet", "/user"})
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
    private static String UPDATE_INFO_PAGE = URL_BUILDER
                                        .addFolder(FolderEnum.USER)
                                        .addPage(WebPageEnum.USER_UPDATE_INFO)
                                        .getURL();
    private static final String RENT_HOUSE_CREATE_PAGE = URL_BUILDER
                                                        .addFolder(FolderEnum.RENT_HOUSE)
                                                        .addPage(WebPageEnum.CREATE_NEW_RENT_HOUSE)
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
        String receiveAction = request.getParameter("action");
        ActionEnum command = ActionEnum.get(receiveAction);
        System.out.println("Served at UserServlet : " + command);

        switch (command) {
            case LOGIN:
                ProcessLogInAction(request, response);
                break;
            case LOGOUT:
                ProcessLogOut(request, response);
                break;
            case REGISTER:
                ProcessRegisterAction(request, response);
                break;
            case UPDATE: 
                request.getRequestDispatcher(UPDATE_INFO_PAGE).forward(request, response);
                break;
            default:
                ProcessDefaultAction(request, response);
        }
    }

    private void ProcessLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        CookieEnum cookieEnum = CookieEnum.REMEMBER_USER;
        String rememeberCookieValue = CookieSupportServlet.processCookie(request, response, cookieEnum, true);
        RememberUserManager.remove(rememeberCookieValue);

        request.getRequestDispatcher(HOMEPAGE).forward(request, response);
    }

    private void ProcessLogInAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        System.out.println("Process Login at [" + getServletName() + "]");
        request.setCharacterEncoding(webpage_tools.PrintTools.getUTF8());
        HttpSession session = request.getSession(true);
        RequestDispatcher requestDispatcher;
        
        Object loginUser = session.getAttribute(AttributeEnum.USER.name());
        if(Objects.nonNull(loginUser)) {
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        Optional<User> user = userDAO.Authenticate(username, password);
        boolean doRememeber = remember != null;

        
        if (user.isPresent()) {
            if (doRememeber) {
                createRememberUserCookie(response, user.get());
            }
            session.setAttribute(AttributeEnum.USER.name(), user.get());
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
        } else {
            ErrorEnum message = ErrorEnum.LOGIN_ERROR;
            request.setAttribute(message.name(), message.getMessage());
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(request, response);
        }
    }

    private void createRememberUserCookie(HttpServletResponse response, User user) {
        String userHashCode = Integer.toString(user.hashCode());
        CookieSupportServlet.addCookie(response, CookieEnum.REMEMBER_USER, userHashCode);
        //Add this user to rememeber maps for later auto-login function with rememberUserCookie
        if (RememberUserManager.get(userHashCode).isEmpty()) {
            RememberUserManager.add(userHashCode, user);
        }
    }

    private void ProcessRegisterAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(REGISTER_PAGE);
        requestDispatcher.forward(request, response);
    }
    
    private void ProcessDefaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Process Default action at UserServlet");
        HttpSession session = request.getSession();
        Object user = session.getAttribute(AttributeEnum.USER.name());
        
        RequestDispatcher requestDispatcher;
        if(user == null) {
            requestDispatcher = request.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(request, response);
        }
        else {
            requestDispatcher = request.getRequestDispatcher(HOMEPAGE);
            requestDispatcher.forward(request, response);
        }
    }

}
