package com.dunef.service.impl;

import com.dunef.dataobject.OrderDetail;
import com.dunef.dto.OrderDTO;
import com.dunef.enums.OrderStatusEnum;
import com.dunef.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午8:46 2017/12/20
 * @Modified By:
 */
@RunWith (SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private String ORDER_ID = "1513818242290471796";


    private final String BUYER_OPENID = "110110";
    private ArrayList<OrderDetail> orderDetailList;
    private OrderDetail ol;
    private OrderDetail ol1;
    private PageRequest request;
    private Page<OrderDTO> orderDTOPage;
    private OrderDTO orderDTO;
    private OrderDTO result;
    private OrderDTO orderDTO1;


    @Test
    public void create() throws Exception {
        orderDTO = new OrderDTO ();
        orderDTO.setBuyerName ( "廖师兄" );
        orderDTO.setBuyerAddress ( "慕课网" );
        orderDTO.setBuyerPhone ( "12312312312" );
        orderDTO.setBuyerOpenid ( BUYER_OPENID );
        //购物车
        orderDetailList = new ArrayList<> ();
        ol = new OrderDetail ();
        ol.setProductId ( "213" );
        ol.setProductQuantity ( 1 );
        ol1 = new OrderDetail ();
        ol1.setProductId ( "123" );
        ol1.setProductQuantity ( 2 );

        orderDetailList.add ( ol );
        orderDetailList.add ( ol1 );

        orderDTO.setOrderDetailList ( orderDetailList );
        result = orderService.create ( orderDTO );
        log.info ( "[创建订单]result={}", result );

        Assert.assertNotNull ( result );

    }

    @Test
    public void findOne() throws Exception {
        result = orderService.findOne ( ORDER_ID );
        log.info ( "查询单个订单 result={}", result );
        Assert.assertEquals ( ORDER_ID, result.getOrderId () );

    }

    @Test
    public void findList() throws Exception {
        request = new PageRequest ( 0, 2 );
        orderDTOPage = orderService.findList ( BUYER_OPENID, request );
        Assert.assertNotEquals ( 0, orderDTOPage.getTotalElements () );
    }

    @Test
    public void cancel() throws Exception {
        orderDTO1 = orderService.findOne ( ORDER_ID );
        result = orderService.cancel ( orderDTO1 );
        Assert.assertEquals ( OrderStatusEnum.CANCEL.getCode (), result.getOrderStatus () );
    }

    @Test
    public void finish() throws Exception {
        orderDTO1 = orderService.findOne ( ORDER_ID );
        result = orderService.finish ( orderDTO1 );
        Assert.assertEquals ( OrderStatusEnum.FINISHED.getCode (), result.getOrderStatus () );
    }

    @Test
    public void paid() throws Exception {
        orderDTO1 = orderService.findOne ( ORDER_ID );
        result = orderService.paid ( orderDTO1 );
        Assert.assertEquals ( PayStatusEnum.SUCCESS.getCode (), result.getPayStatus () );
    }
}