package com.dunef.repository;

import com.dunef.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午9:32 2017/12/18
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;
    private ProductInfo productInfo;
    private ProductInfo productInfo1;
    private List<ProductInfo> productInfoList;

    @Test
    public  void saveTest(){
        productInfo = new ProductInfo ();
            productInfo.setProductId  ( "123" );
            productInfo.setProductName ( "皮蛋粥" );
            productInfo.setProductPrice (new BigDecimal ( 3.2 ));
            productInfo.setProductStock ( 100 );
            productInfo.setProductDescription ( "很好喝" );
            productInfo.setProductIcon ( "http://www.dunef.com" );
            productInfo.setProductStatus ( 0 );
            productInfo.setCategoryType ( 5 );

        productInfo1 = repository.save ( productInfo );
        Assert.assertNotNull (productInfo1);

    }

    @Test
    public void findByProductStatus()throws Exception{
        productInfoList = repository.findByProductStatus ( 0 );
        Assert.assertNotEquals ( 0,productInfoList.size () );
    }



}