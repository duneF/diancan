package com.dunef.repository;

import com.dunef.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:29 2017/12/14
 * @Modified By:
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> category_typeList);



















}
