package com.dunef.repository;

import com.dunef.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:52 2017/12/19
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
public class OrderDatailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;
    private OrderDetail orderDetail;
    private OrderDetail request;
    private List<OrderDetail> byOrderId;

    @Test
    public void saveTest(){
        orderDetail = new OrderDetail ();
        orderDetail.setDetailId ( "123456788" );
        orderDetail.setOrderId ( "11112" );
        orderDetail.setProductIcon ( "http://www.image.funef.com" );
        orderDetail.setProductId ( "111112" );
        orderDetail.setProductName ( "DA米粥" );
        orderDetail.setProductPrice ( new BigDecimal ( 3.2 ) );
        orderDetail.setProductQuantity ( 3 );
        request = repository.save ( orderDetail );
        Assert.assertNotNull (request);

    }




    @Test
    public void findByOrderId() throws Exception {
        byOrderId = repository.findByOrderId ( "11111" );
        Assert.assertNotEquals ( 0,byOrderId.size () );
    }

}