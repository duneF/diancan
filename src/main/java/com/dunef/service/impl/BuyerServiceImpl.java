package com.dunef.service.impl;

import com.dunef.dto.OrderDTO;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.service.BuyerService;
import com.dunef.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午10:24 2017/12/25
 * @Modified By:
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    private OrderService orderService;
    private OrderDTO orderDTO;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return  checkOrderOwner ( openid,orderId );
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        orderDTO=checkOrderOwner ( openid,orderId );
        if (orderDTO==null){
            log.error ( "取消订单 查不到该订单,orderId={}",orderId );
            throw new SellException ( ResultEnum.ORDER_NOT_EXIST );
        }
        return orderService.cancel ( orderDTO );
    }

    private OrderDTO checkOrderOwner(String openid,String orderId){
        orderDTO = orderService.findOne ( orderId );
        if (orderDTO==null){
            return null;
        }
        //判断是否是自己的订单
        if (orderDTO.getBuyerOpenid ().equalsIgnoreCase ( openid )){
            log.error ( "查询订单 订单的openid不一致，openid={},orderDTO={} ",openid,orderDTO );
            throw new SellException ( ResultEnum.ORDER_OWNER_ERROR );
        }
        return orderDTO;
    }
}
