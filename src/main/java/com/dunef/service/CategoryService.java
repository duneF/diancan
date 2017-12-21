package com.dunef.service;

import com.dunef.dataobject.ProductCategory;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午8:43 2017/12/17
 * @Modified By:
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
