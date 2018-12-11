package web.Controller;

import Util.WebUtil;
import domain.Category;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageServlet extends HttpServlet {

    private BusinessService s = new BusinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if ("addCategory".equals(op)) {
            addCategory(request,response);
        }else if ("showAllCategorys".equals(op)){
            showAllCategorys(request,response);
        }
    }

    private void showAllCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cs = s.findAllCategorys();
        request.setAttribute("cs",cs);
        request.getRequestDispatcher("/manage/listCategory.jsp").forward(request,response);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category c = WebUtil.fillBean(request,Category.class);
        s.addCategory(c);
        //处理完成后，要页面转向Redirect After Post
        response.sendRedirect(request.getContextPath()+"/common/message.jsp");
    }
}
