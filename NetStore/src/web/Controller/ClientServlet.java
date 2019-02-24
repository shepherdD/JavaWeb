package web.Controller;

import commons.Page;
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

@WebServlet(name = "ClientServlet")
public class ClientServlet extends HttpServlet {

    private BusinessService s = new BusinessServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        if ("showIndex".equals(op)){
            showIndex(request,response);
        } else if ("showCategoryBooks".equals(op)) {
            showCategoryBooks(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void showIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        List<Category> cs = s.findAllCategorys();
        request.setAttribute("cs",cs);
        String num = request.getParameter("num");
        Page page = s.findBookPageByPageRecords(num);
        page.setUrl("/client/ClientServlet?op=showIndex");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/listBooks.jsp").forward(request,response);
    }

    private void showCategoryBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<Category> cs = s.findAllCategorys();
        request.setAttribute("cs",cs);
        String num = request.getParameter("num");
        String categoryId = request.getParameter("categoryId");
        Page page = s.findBookPageByPageRecords(num,categoryId);
        page.setUrl("/client/ClientServlet?op=showCategoryBooks&categoryId="+categoryId);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/listBooks.jsp").forward(request,response);
    }
}
