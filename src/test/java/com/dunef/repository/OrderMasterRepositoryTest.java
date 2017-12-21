package com.dunef.repository;

import com.dunef.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午4:18 2017/12/19
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    private OrderMaster orderMaster;
    private OrderMaster request;

    private final String OPENID="110110";
    private Page<OrderMaster> byBuyerOpenid;

    @Test
    public void saveTest(){
        orderMaster = new OrderMaster ();
        orderMaster.setOrderId ( "123547" );
        orderMaster.setBuyerName ( "师兄" );
        orderMaster.setBuyerPhone ( "13300003333" );
        orderMaster.setBuyerAddress ( "慕课网" );
        orderMaster.setBuyerOpenid ( OPENID);
        orderMaster.setOrderAmount ( new BigDecimal ( 2.5 ) );
        request = repository.save ( orderMaster );
        Assert.assertNotNull (request);
    }
    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request=new PageRequest ( 0,3);
        byBuyerOpenid = repository.findByBuyerOpenid ( OPENID, request );
        Assert.assertNotEquals ( 0,byBuyerOpenid.getTotalElements () );
    }

}