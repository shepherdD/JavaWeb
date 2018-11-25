package service.impl;

import Util.IdGenertor;
import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import domain.Category;
import service.BusinessService;

import java.util.List;

public class BusinessServiceImpl implements BusinessService {

    private CategoryDao categorydao = new CategoryDaoImpl();

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
}
