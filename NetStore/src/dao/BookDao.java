package dao;

import domain.Book;

public interface BookDao {
    void save(Book book);

    Book findBookById(String bookId);
}
