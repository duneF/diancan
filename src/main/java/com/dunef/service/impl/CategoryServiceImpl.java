package com.dunef.service.impl;

import com.dunef.dataobject.ProductCategory;
import com.dunef.repository.ProductCategoryRepository;
import com.dunef.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午8:47 2017/12/17
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne ( categoryId );
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll ();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn ( categoryTypeList );
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save ( productCategory );
    }
}
