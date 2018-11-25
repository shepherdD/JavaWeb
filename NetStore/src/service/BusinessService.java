package service;

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
}
