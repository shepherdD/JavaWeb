package dao;

import domain.Book;

import java.util.List;

public interface BookDao {
    void save(Book book);

    Book findBookById(String bookId);

    int getTotalRecordsNum();

    List findPageRecords(int startIndex, int pageSize);

    int getTotalRecordsNum(String categoryId);

    List findPageRecords(int startIndex, int pageSize, String categoryId);
}
