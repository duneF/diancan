package com.dunef.converter;

import com.dunef.dataobject.OrderDetail;
import com.dunef.dto.OrderDTO;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午7:13 2017/12/21
 * @Modified By:
 */
@Slf4j
public class OrderForm2OderDTOConverter {

    private static OrderDTO orderDTO;
    private static Gson gson;
    private static ArrayList<OrderDetail> orderDetailList;

    public static OrderDTO convert(OrderForm orderForm) {
        gson = new Gson ();
        orderDTO = new OrderDTO ();
        orderDTO.setBuyerName ( orderForm.getName () );
        orderDTO.setBuyerPhone ( orderForm.getPhone () );
        orderDTO.setBuyerAddress ( orderForm.getAddress () );
        orderDTO.setBuyerOpenid ( orderForm.getOpenid () );


        orderDetailList = new ArrayList<> ();

        //仔细再看一次返回json的格式//TODO
        try {

            orderDetailList = gson.fromJson ( orderForm.getItems ()
                    , new TypeToken<List<OrderDetail>> () {
                    }.getType () );
        } catch (Exception e) {
            log.error ( "对象转换  错误，String={}", orderForm.getItems () );
            throw new SellException ( ResultEnum.PARAM_ERROR );
        }
        orderDTO.setOrderDetailList (orderDetailList);
        return orderDTO;
    }
}
