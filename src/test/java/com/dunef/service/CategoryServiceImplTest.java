package com.dunef.service;

import com.dunef.dataobject.ProductCategory;
import com.dunef.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午9:04 2017/12/17
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
     private CategoryServiceImpl categoryService;
    private ProductCategory productCategory;
    private List<ProductCategory> all;
    private List<ProductCategory> byCategoryTypeIn;
    private List<ProductCategory> productCategories;
    private ProductCategory productCategory1;
    private ProductCategory save;


    @Test
    public void findOne() throws Exception {
        productCategory = categoryService.findOne ( 9 );
        Assert.assertEquals ( new Integer ( 9 ),productCategory.getCategoryId () );
    }

    @Test
    public void findAll() throws Exception {
        all = categoryService.findAll ();
        Assert.assertNotEquals ( 0,all.size () );
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        productCategories = byCategoryTypeIn = categoryService.findByCategoryTypeIn ( Arrays.asList ( 2, 3, 4 ) );
        Assert.assertNotEquals ( 0, productCategories);
    }

    @Test
    public void save() throws Exception {
        productCategory1 = new ProductCategory ( "秦皇武帝", 99 );
        save = categoryService.save ( productCategory1 );
        Assert.assertNotNull ( save );

    }

}