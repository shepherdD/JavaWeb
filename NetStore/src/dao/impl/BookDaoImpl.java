package dao.impl;

import Util.DBCPUtil;
import dao.BookDao;
import domain.Book;
import domain.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class BookDaoImpl implements BookDao {

    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

    @Override
    public void save(Book book) {
        try {
            qr.update("insert into books(id,name,author,price,path,filename,description," +
                            "categoryId) " +
                    "values (?,?,?,?,?,?,?,?)",book.getId(),book.getName(),book.getAuthor(),
                    book.getPrice(),book.getPath(),book.getFilename(),book.getDescription(),
                    book.getCategory().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findBookById(String bookId) {
        try {
            Book book =  qr.query("select * from books where id = ?",
                    new BeanHandler<Book>(Book.class),bookId);
            if (book != null) {
                Category c = qr.query("select * from categorys where id = (select categoryId from" +
                        " books where id = ?)",new BeanHandler<Category>(Category.class),bookId);
                book.setCategory(c);
            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
