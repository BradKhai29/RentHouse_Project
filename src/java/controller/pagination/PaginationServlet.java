package controller.pagination;

import controller.validation.InputValidation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import support_enum.AttributeEnum;
import support_enum.ServletEnum;
import webpage_tools.URLBuilderFactory;

@WebServlet(name = "PaginationServlet", urlPatterns = {"/page"})
public class PaginationServlet extends HttpServlet {
    private static final String HOMEPAGE = URLBuilderFactory.getURL(ServletEnum.HOME);
    private static final int offset = 9;
    private static int maxPage;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        maxPage = (Integer)request.getServletContext().getAttribute(AttributeEnum.maxPage.name());
        
        int currentPageNum = InputValidation.getNumber(request.getParameter("page"));
        if(currentPageNum == InputValidation.default_value.getValue()) {
            currentPageNum = (Integer)session.getAttribute(AttributeEnum.currentPageNum.name());
        }
        int upperPageNum = 1;
        int lowerPageNum = 1;

        String nextPage = request.getParameter("next");
        String prevPage = request.getParameter("prev");
        
        if (currentPageNum != InputValidation.default_value.getValue()) {
            setUpperAndLowPageNum(session, currentPageNum, upperPageNum, lowerPageNum);
        }

        if (nextPage != null) {
            currentPageNum = (Integer) session.getAttribute(AttributeEnum.currentPageNum.name());
            if (currentPageNum < maxPage) {
                currentPageNum++;
            }
            setUpperAndLowPageNum(session, currentPageNum, upperPageNum, lowerPageNum);
        }

        if (prevPage != null) {
            currentPageNum = (Integer) session.getAttribute(AttributeEnum.currentPageNum.name());
            if (currentPageNum > 1) {
                currentPageNum--;
            }
            setUpperAndLowPageNum(session, currentPageNum, upperPageNum, lowerPageNum);
        }
        request.getRequestDispatcher(HOMEPAGE).forward(request, response);
    }
    
    private void setUpperAndLowPageNum(HttpSession session, int currentPageNum, int upperPageNum, int lowerPageNum) {
        upperPageNum = currentPageNum * offset;
        lowerPageNum = upperPageNum - offset + 1;
        session.setAttribute(AttributeEnum.currentPageNum.name(), currentPageNum);
        session.setAttribute(AttributeEnum.lowerPageNum.name(), lowerPageNum);
        session.setAttribute(AttributeEnum.upperPageNum.name(), upperPageNum);
    }
}
