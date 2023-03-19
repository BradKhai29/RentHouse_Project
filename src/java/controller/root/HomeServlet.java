package controller.root;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import support_enum.WebPageEnum;
import webpage_tools.URLBuilderFactory;


public class HomeServlet extends HttpServlet {
    private final static String HOMEPAGE = URLBuilderFactory.get().addPage(WebPageEnum.HOME).getURL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher homeDispatcher = request.getRequestDispatcher(HOMEPAGE);
        homeDispatcher.forward(request, response);
    } 
}
