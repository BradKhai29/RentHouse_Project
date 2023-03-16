package controller.user;
import controller.validation.InputValidation;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.user.*;
import webpage_tools.PrintTools;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;
import support_enum.*;



@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet", "/register"})
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final UserDAO userDAO;
    private static final URLBuilder urlBuiler = URLBuilderFactory.get();
    private static final String registerPageURL;
    
    static {
    	userDAO = new UserDAO();
    	//
    	urlBuiler.addFolder(FolderEnum.USER).addPage(WebPageEnum.USER_REGISTER);
    	registerPageURL = urlBuiler.getURL();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	session.removeAttribute(AttributeEnum.STEP2.name());
    	
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(registerPageURL);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName()+ "]");
        request.setCharacterEncoding(PrintTools.getUTF8());
        response.setCharacterEncoding(PrintTools.getUTF8());
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        String processStep = request.getParameter("register");
        AttributeEnum step = AttributeEnum.getEnum(processStep);
        
        switch (step)
        {
            case STEP1:
                processStep1(request, response);
                break;
            case STEP2:
                processStep2(request, response);
                break;
            default:
                session.removeAttribute(AttributeEnum.STEP2.name());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(registerPageURL);
                requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Step 2 include info about fullname, address, email, phone number
     * <br>Have checking phone number
     * @param request
     * @param response
     * @throws IOException 
     */
    private void processStep1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        System.out.println("Process step 1");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeEnum.REGISTER_USER.name());
        if(user == null) user = new User();
        
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String userRole = request.getParameter("userRole");
        
        boolean isMale = gender.equals("1");
        boolean isProvider = userRole.equals("1");
        user.setFullname(fullname);
        user.setGender(isMale);
        user.setUserRole(isProvider);
        
        boolean testPhoneNumber = InputValidation.checkPhoneNumber(phoneNumber);
        if(testPhoneNumber)
        {
            session.setAttribute(AttributeEnum.STEP2.name(), "");
            user.setPhoneNumber(phoneNumber);
            request.removeAttribute(ErrorEnum.INVALID_PHONE_NUMBER.name());
        }
        else request.setAttribute(ErrorEnum.INVALID_PHONE_NUMBER.name(), ErrorEnum.INVALID_PHONE_NUMBER.getMessage());
        
        session.setAttribute(AttributeEnum.REGISTER_USER.name(), user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(registerPageURL);
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Process step 2 includes username, password
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void processStep2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        System.out.println("Process step 2");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeEnum.REGISTER_USER.name());
    
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        boolean testExistUsername = userDAO.isExistUsername(username);
        boolean testConfirmPassword = checkConfirmPassword(password, confirmPassword);
        
        boolean allTest = !testExistUsername && testConfirmPassword;
        ErrorEnum message;
        
        if(testExistUsername) {
        	message = ErrorEnum.EXIST_USERNAME;
        	session.setAttribute(message.name(), message.getMessage());
        }
        
        if (testConfirmPassword == false) {
			message = ErrorEnum.CONFIRM_PASSWORD_NOT_MATCH;
			session.setAttribute(message.name(), message.getMessage());
		}
        
        if (allTest) {
        	session.removeAttribute(AttributeEnum.STEP2.name());
        	user.setUsername(username);
        	user.setPassword(confirmPassword);
        	
        	userDAO.insert(user);        	
        	
        	session.removeAttribute(AttributeEnum.REGISTER_USER.name());
        	session.setAttribute(AttributeEnum.USER.name(), user);
        	
			urlBuiler.addPage(WebPageEnum.HOME);
			String homeURL = urlBuiler.getURL();
			request.getRequestDispatcher(homeURL).forward(request, response);
		}
        else {
        	request.getRequestDispatcher(registerPageURL).forward(request, response);
		}
    }
    
    public boolean checkConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
