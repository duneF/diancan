//package com.dunef.service;
//
//import com.dunef.dataobject.ProductInfo;
//import com.dunef.enums.ProductStatusEnum;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * @Author:dunef
// * @Description:
// * @Date:Created in 上午10:35 2017/12/18
// * @Modified By:
// */
//@RunWith (SpringRunner.class)
//@SpringBootTest
//public class ProductServiceImplTest {
//    @Autowired
//    private ProductServiceImpl productService;
//    private ProductInfo info;
//    private List<ProductInfo> upAll;
//    private PageRequest pageRequest;
//    private Page<ProductInfo> productInfoPage;
//    private ProductInfo info1;
//    private ProductInfo productInfo;
//    private ProductInfo info2;
//
//    @Test
//    public void findOne() throws Exception {
//        info = productService.findOne ( "123" );
//        Assert.assertEquals ("123",info.getProduct_id ());
//
//    }
//
//    @Test
//    public void findUpAll() throws Exception {
//        upAll = productService.findUpAll ();
//        for (ProductInfo productInfo1 : upAll) {
//            System.out.println (productInfo1.toString ());
//        }
//        Assert.assertNotEquals ( 0,upAll.size () );
//    }
//
//    @Test
//    public void findAll() throws Exception {
//        pageRequest = new PageRequest ( 0, 2 );
//
//        productInfoPage = productService.findAll ( pageRequest );
//        System.out.println (productInfoPage.getTotalElements ());
//        Assert.assertNotEquals ( 0,productInfoPage.getTotalElements () );
//    }
//
//    @Test
//    public void save() throws Exception {
//        productInfo = new ProductInfo ();
//        productInfo.setProduct_id ( "12455" );
//        productInfo.setProductName ( "皮蛋粥" );
//        productInfo.setProductPrice (new BigDecimal ( 3.2 ) );
//        productInfo.setProductStock ( 100 );
//        productInfo.setProductDescription ( "很好喝" );
//        productInfo.setProductIcon ( "http://www.dunef.com" );
//        productInfo.setProductStatus ( 0 );
//        productInfo.setCategoryType ( ProductStatusEnum.UP.getCode () );
//        info2 = productService.save ( productInfo );
//        Assert.assertNotNull ( info2 );
//    }
//
//}