package com.dunef.converter;

import com.dunef.dataobject.OrderMaster;
import com.dunef.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午9:42 2017/12/21
 * @Modified By:
 */
public class OrderMaster20rederDTOConverter {

    private static OrderDTO orderDTO;

    public  static OrderDTO convert(OrderMaster orderMaster){
        orderDTO = new OrderDTO ();
        BeanUtils.copyProperties (orderMaster,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return     orderMasterList.stream ().map ( e->
                convert (e)
            ).collect ( Collectors.toList () );
    }
}
