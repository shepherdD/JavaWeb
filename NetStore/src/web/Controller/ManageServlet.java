package web.Controller;

import Util.IdGenertor;
import Util.WebUtil;
import commons.Page;
import domain.Book;
import domain.Category;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import service.BusinessService;
import service.impl.BusinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
        }else if ("addBookUI".equals(op)) {
            addBookUI(request,response);
        }else if ("addBook".equals(op)) {
            addBook(request,response);
        } else if ("showPageBooks".equals(op)) {
            showPageBooks(request,response);
        }
    }

    private void showPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("num");
        Page page = s.findBookPageByPageRecords(num);
        page.setUrl("/manage/ManageServlet?op=showPageBooks");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/manage/listBooks.jsp").forward(request,response);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category c = WebUtil.fillBean(request,Category.class);
        s.addCategory(c);
        //处理完成后，要页面转向Redirect After Post
        response.sendRedirect(request.getContextPath()+"/common/message.jsp");
    }

    private void showAllCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cs = s.findAllCategorys();
        request.setAttribute("cs",cs);
        request.getRequestDispatcher("/manage/listCategory.jsp").forward(request,response);
    }

    private void addBookUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> cs = s.findAllCategorys();
        request.setAttribute("cs",cs);
        request.getRequestDispatcher("/manage/addBook.jsp").forward(request,response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 判断表格内容是不是multipart/form-data类型的
        Boolean isMulitipart = ServletFileUpload.isMultipartContent(request);
        if (!isMulitipart) {
            throw new RuntimeException("This is not multipart/form-data");
        }
        // 解析请求内容
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        List<FileItem> items = new ArrayList<FileItem>();
        try {
            items = sfu.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        Book book = new Book();
        for (FileItem item : items) {
            if (item.isFormField()) {
                processFormFiled(item,book);
            } else {
                processUploadFiled(item,book);
            }
        }

        s.addBook(book);
        response.sendRedirect(request.getContextPath()+"/common/message.jsp");
    }

    private void processFormFiled(FileItem item, Book book) {
        try {
            String filedName = item.getFieldName();
            String filedValue = item.getString("UTF-8");
            BeanUtils.setProperty(book,filedName,filedValue);
            if ("categoryId".equals(filedName)) {
                Category c = s.findCategoryById(filedValue);
                book.setCategory(c);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processUploadFiled(FileItem item, Book book) {
        // 存放路径:不要放在WEB-INF中
        String storeDirectory = getServletContext().getRealPath("/images");
        File rootDirectory = new File(storeDirectory);
        if (!rootDirectory.exists()) {
            rootDirectory.mkdirs();
        }
        // 文件名
        String fileName = item.getName();
        if (fileName != null) {
            fileName = IdGenertor.genGUID()+"."+ FilenameUtils.getExtension(fileName);
            book.setFilename(fileName);
        }
        // 计算子目录
        String path = genChildDirectory(storeDirectory,fileName);
        book.setPath(path);
        // 文件上传
        try {
            item.write(new File(rootDirectory,path+"/"+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String genChildDirectory(String realPath, String fileName) {
        int hashCode = fileName.hashCode();
        int dir1 = hashCode&0xf;
        int dir2 = (hashCode&0xf0)>>4;
        String str = dir1+File.separator+dir2;
        File file = new File(realPath,str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }


}
