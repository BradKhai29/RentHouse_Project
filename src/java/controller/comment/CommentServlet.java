package controller.comment;

import controller.validation.InputValidation;
import entity.DAO.IDao;
import entity.FactoryDAO.FactoryDAO;
import entity.comment.Comment;
import entity.rent_house.RentHouse;
import entity.user.User;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
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


@WebServlet(name="CommentServlet", urlPatterns={"/user/comment"})
public class CommentServlet extends HttpServlet {
    private static final URLBuilder urlBuilder = URLBuilderFactory.get();
    private static final String HOMEPAGE = urlBuilder
                                           .addPage(WebPageEnum.HOME)
                                           .getURL();
    private static final String RENT_HOUSE_DETAIL_PAGE = urlBuilder
                                                        .addFolder(FolderEnum.RENT_HOUSE)
                                                        .addPage(WebPageEnum.RENT_HOUSE_DETAIL)
                                                        .getURL();
    private static IDao<Comment> commentDAO = FactoryDAO.getDao(FactoryDAO.COMMENT);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }        
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {     
        String inputAction = request.getParameter("action");
        ActionEnum action = ActionEnum.get(inputAction);
        
        System.out.println(action);
        switch (action) {
            case CANCEL: {
                    request.getSession().removeAttribute(ActionEnum.CREATE.name());
                    processDefaultAction(request, response);
                }
                break;
            case COMMENT:
                processCommentAction(request, response);
                break;
            case EDIT:
                processEditAction(request, response);
                break;
            case CONFIRM_UPDATE:
                processEditAction(request, response);
                break;
            case DELETE:
                processDeleteAction(request, response);
                break;
            default:
                processDefaultAction(request, response);
        }
    }
    
    private RentHouse getRentHouse(HttpSession session) {
        return (RentHouse)session.getAttribute(AttributeEnum.rentHouse.name());
    }
    
    private void processCommentAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeEnum.USER.name());
        RentHouse rentHouse = getRentHouse(session);
        
        String commentDetails = request.getParameter("details");
        int houseID = rentHouse.getHouseID();
        int userID = user.getUserID();
        
        PrintTools.print(user, rentHouse, commentDetails);
        
        Comment comment = new Comment(1, 5, commentDetails, userID, houseID);
        comment.setCommentID(comment.hashCode());
        commentDAO.insert(comment);
        
        rentHouse.getCommentMap().put(comment.hashCode(), comment);
        request.getRequestDispatcher(RENT_HOUSE_DETAIL_PAGE).forward(request, response);
    }
    
    private void processEditAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object edit = session.getAttribute(ActionEnum.EDIT.name());
        
        boolean editIsNull = (edit == null);
        if(editIsNull) {
            System.out.println("Set edit");
            session.setAttribute(ActionEnum.EDIT.name(), "");
        }
        else {
            System.out.println("Do edit");
            String newDetails = request.getParameter("details");
            String inputCommentID = request.getParameter("commentID");
            
            int commentID = InputValidation.getNumber(inputCommentID);
            Optional<Comment> comment = commentDAO.get(commentID);
            if(comment.isPresent()) {
                System.out.println("Do edit in dao");
                comment.get().setDetails(newDetails);
                commentDAO.update(comment.get());
            }
            session.removeAttribute(ActionEnum.EDIT.name());
        }
        request.getRequestDispatcher(RENT_HOUSE_DETAIL_PAGE).forward(request, response);
    }
    
    private void processDeleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputCommentID = request.getParameter("commentID");
        int commentID = InputValidation.getNumber(inputCommentID);
        commentDAO.delete(commentID);
        HttpSession session = request.getSession();
        
        RentHouse rentHouse = (RentHouse)session.getAttribute(AttributeEnum.rentHouse.name()); 
        rentHouse.getCommentMap().remove(commentID);
        processDefaultAction(request, response);
    }
    
    private void processDefaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RentHouse rentHouse = getRentHouse(session);
        if(rentHouse == null) {
            request.getRequestDispatcher(HOMEPAGE).forward(request, response);
        }
        else request.getRequestDispatcher(RENT_HOUSE_DETAIL_PAGE).forward(request, response);    
    }
}
