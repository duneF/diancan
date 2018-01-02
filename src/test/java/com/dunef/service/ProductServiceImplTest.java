package com.dunef.service;

import com.dunef.dataobject.ProductInfo;
import com.dunef.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:54 2018/1/2
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
        @Autowired
        private ProductService productService;
    private ProductInfo result;
    @Test
    public void  onSale(){
        result = productService.onSale ( "124" );
        Assert.assertEquals ( ProductStatusEnum.UP,result.getProductStatusEnum ());
    }
    @Test
    public void  offSale(){
        result = productService.offSale ( "124" );
        Assert.assertEquals ( ProductStatusEnum.DOWN,result.getProductStatusEnum ());
    }

}