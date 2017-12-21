package com.dunef.repository;

import com.dunef.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午5:04 2017/12/14
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;
    private ProductCategory repositoryOne;
    private ProductCategory productCategory;
    private ProductCategory productCategory1;
    private ProductCategory one;
    private ProductCategory save1;
    private List<Integer> list;
    private List<ProductCategory> byCateGoryTypeIn;
    private List<ProductCategory> byCateGoryTypeIn1;

//    @Test
//    public void findOneTe(){
//        repositoryOne = repository.findOne ( 7 );
//        System.out.println (repositoryOne.toString ());
//    }
//    @Test
//    public void saveTest(){
//        productCategory = new ProductCategory ("男生爱",12);
//        productCategory1 = repository.save ( productCategory );
//        Assert.assertNotNull ( productCategory1 );
//    }
//    @Test
//    public void saveUpdataTime(){
//        one = repository.findOne ( 9 );
//        one.setCategory_type ( 26);
//     //   one.setUpdate_time ( new Date (  ) );
//        save1 = repository.save ( one );
//        Assert.assertNotNull ( save1 );
//    }

    @Test
    public void findBycategory_type(){
        byCateGoryTypeIn1 = repository.findByCategoryTypeIn ( Arrays.asList ( 2, 3 ,8) );
        Assert.assertNotEquals ( 0,byCateGoryTypeIn1.size () );


    }














}