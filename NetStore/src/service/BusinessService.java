package service;

import commons.Page;
import domain.Book;
import domain.Category;

import java.util.List;

public interface BusinessService {

    /**
     * 添加分类
     * @param c
     */
    void addCategory(Category c);

    /**
     * 查询所有的分类
     * @return
     */
    List<Category> findAllCategorys();

    /**
     * 根据id查询分类
     * @param categoryId
     * @return 没有找到返回null
     */
    Category findCategoryById(String categoryId);

    /**
     * 添加书籍
     * @param book
     */
    void addBook(Book book);

    /**
     * 根据ID查询书籍
     * @param bookId
     * @return
     */
    Book findBookById(String bookId);

    /**
     * 根据用户要查看的页码，返回封装了所有与分页对象有关的Page对象
     * @param num 要看的页码，如果为null或""，默认为1
     * @return
     */
    Page findBookPageByPageRecords(String num);
    Page findBookPageByPageRecords(String num,String categoryId);
}
