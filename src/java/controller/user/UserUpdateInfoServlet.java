package controller.user;

import entity.DAO.IDao;
import entity.FactoryDAO.FactoryDAO;
import entity.user.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support_enum.ActionEnum;
import support_enum.AttributeEnum;
import support_enum.FolderEnum;
import support_enum.WebPageEnum;
import webpage_tools.PrintTools;
import webpage_tools.URLBuilder;
import webpage_tools.URLBuilderFactory;


@WebServlet(name="UserUpdateInfoServlet", urlPatterns={"/user/update"})
public class UserUpdateInfoServlet extends HttpServlet {
    private static final IDao<User> userDAO;
    private static final URLBuilder URL_BUILDER = URLBuilderFactory.get();

    private static String HOMEPAGE = URL_BUILDER.addPage(WebPageEnum.HOME).getURL();
    private static String UPDATE_INFO_PAGE = URL_BUILDER
                                        .addFolder(FolderEnum.USER)
                                        .addPage(WebPageEnum.USER_UPDATE_INFO)
                                        .getURL();
    static {
        userDAO = FactoryDAO.getDao(FactoryDAO.USER);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        removeUnusedAttribute(session);
        
        String cancel = request.getParameter("cancel");
        if(cancel == null) request.getRequestDispatcher(HOMEPAGE).forward(request, response);
        else request.getRequestDispatcher(UPDATE_INFO_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Served at [" + getServletName() + "]");
        request.setCharacterEncoding(PrintTools.getUTF8());
        request.getSession().removeAttribute("success");
        request.getSession().removeAttribute("message");
        
        String command = request.getParameter("action");
        ActionEnum updateCommandEnum = ActionEnum.get(command);

        switch (updateCommandEnum) 
        {
            case UPDATE:
                updateProfile(request);
                break;
            case UPDATE_PASSWORD:
                updatePassword(request);
                break;
            case CONFIRM_UPDATE:
                confirmUpdate(request);
                break;
            default:
        }
        request.getRequestDispatcher(UPDATE_INFO_PAGE).forward(request, response);
    }

    private void updateProfile(HttpServletRequest request)
    {
        String updateProfile = "";
        request.getSession().setAttribute(AttributeEnum.UPDATE_PROFILE_CHECKPOINT.name(), updateProfile);
    }

    private void confirmUpdate(HttpServletRequest request) 
    {
        HttpSession session = request.getSession();
        
        User user = (User)session.getAttribute(AttributeEnum.USER.name());
        boolean isUpdateProfile = session.getAttribute(AttributeEnum.UPDATE_PROFILE_CHECKPOINT.name()) != null;
        boolean isUpdatePassword = session.getAttribute(AttributeEnum.UPDATE_PASSWORD_CHECKPOINT.name()) != null;
        
        //Init message
        String message = "MẬT KHẨU NHẬP LẠI KHÔNG ĐÚNG, VUI LÒNG NHẬP LẠI";
        if(isUpdateProfile)
        {
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String gender = request.getParameter("gender");
            
            user.setFullname(fullname);
            user.setPhoneNumber(phoneNumber);
            user.setGender(Boolean.parseBoolean(gender));
            
            userDAO.update(user);
            message = "CẬP NHẬT THÔNG TIN THÀNH CÔNG";
            request.getSession().removeAttribute(AttributeEnum.UPDATE_PROFILE_CHECKPOINT.name());
            request.getSession().setAttribute("message", message);
            session.setAttribute("success", "");            
        }
        
        if (isUpdatePassword) 
        {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmNewPassword = request.getParameter("confirmNewPassword");
            
            if(oldPassword.equals(user.getPassword())) {
                boolean isSimilar = confirmNewPassword.equals(newPassword);
                
                if (isSimilar) {
                    message = "THAY ĐỔI MẬT KHẨU THÀNH CÔNG";
                    session.setAttribute("success", "");
                    
                    user.setPassword(newPassword);
                    userDAO.update(user);
                    request.getSession().removeAttribute(AttributeEnum.UPDATE_PASSWORD_CHECKPOINT.name());
                }
            }
            else{
                message = "MẬT KHẨU CŨ KHÔNG ĐÚNG, VUI LÒNG NHẬP LẠI";
            }
            
            session.setAttribute("message", message);
        }
    }
    
    private void updatePassword(HttpServletRequest request)
    {
        String updatePassword = "";
        request.getSession().setAttribute(AttributeEnum.UPDATE_PASSWORD_CHECKPOINT.name(), updatePassword);
    }
    
    private void removeUnusedAttribute(HttpSession session){
        session.removeAttribute("message");
        session.removeAttribute("success");
        session.removeAttribute(AttributeEnum.UPDATE_PROFILE_CHECKPOINT.name());
        session.removeAttribute(AttributeEnum.UPDATE_PASSWORD_CHECKPOINT.name());
    }

}
