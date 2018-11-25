package dao;

import domain.Category;

import java.util.List;

public interface CategoryDao {
    void save(Category c);

    List<Category> findAll();

    Category findById(String categoryId);
}
