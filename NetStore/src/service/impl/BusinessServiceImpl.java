package service.impl;

import Util.IdGenertor;
import commons.Page;
import dao.BookDao;
import dao.CategoryDao;
import dao.impl.BookDaoImpl;
import dao.impl.CategoryDaoImpl;
import domain.Book;
import domain.Category;
import service.BusinessService;

import java.util.List;

public class BusinessServiceImpl implements BusinessService {

    private CategoryDao categorydao = new CategoryDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addCategory(Category c) {
        c.setId(IdGenertor.genGUID());
        categorydao.save(c);
    }

    @Override
    public List<Category> findAllCategorys() {
        return categorydao.findAll();
    }

    @Override
    public Category findCategoryById(String categoryId) {
        return categorydao.findById(categoryId);
    }

    @Override
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("The book can not be null");
        }
        if (book.getCategory() == null) {
            throw new IllegalArgumentException("The book's category can not be bull");
        }
        book.setId(IdGenertor.genGUID());
        bookDao.save(book);
    }

    @Override
    public Book findBookById(String bookId) {
        return bookDao.findBookById(bookId);
    }

    @Override
    public Page findBookPAgeByPageRecords(String num) {
        return null;
    }
}
