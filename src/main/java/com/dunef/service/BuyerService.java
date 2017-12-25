package com.dunef.service;

import com.dunef.dto.OrderDTO;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午10:22 2017/12/25
 * @Modified By:
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);

}
