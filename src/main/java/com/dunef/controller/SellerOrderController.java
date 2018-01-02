package com.dunef.controller;

import com.dunef.dto.OrderDTO;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author:dunef
 * @Description:卖家端订单
 * @Date:Created in 下午8:34 2017/12/25
 * @Modified By:
 */
@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
        @Autowired
        private OrderService orderService;
    private PageRequest pageRequest;
    private Page<OrderDTO> orderDTOPage;
    private OrderDTO orderDTO;

    /**
     *展示订单
     * @param page 第几页，从1页开始
     * @param size 一页有多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "30") Integer size,
                             Map<String,Object> map){

        pageRequest = new PageRequest ( page-1, size );
        orderDTOPage = orderService.findList ( pageRequest );
        map.put ( "size",size );
        map.put ( "currentPage",page );
        map.put ( "orderDTOPage",orderDTOPage );
        return new ModelAndView ( "order/list",map );
    }

    /**
     * 取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                                Map<String,Object> map){
        try{
        orderDTO = orderService.findOne ( orderId );
        orderService.cancel ( orderDTO );
        }catch (SellException e){
            log.error ( "卖家端取消订单 发生异常{}",e );
            map.put ( "msg", e.getMessage ());
            map.put ( "url","/dianCan/seller/order/list");
            return new ModelAndView ( "common/error",map);
        }
        map.put ( "msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage ());
        map.put ( "url","/dianCan/seller/order/list");
      //  orderService.cancel ( orderDTO );

        return new ModelAndView ("common/success");
    }

    /**
     * 查询详情
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId") String orderId,
                               Map<String,Object>map){
        try {
            orderDTO=orderService.findOne ( orderId );
        } catch (Exception e) {
         log.error ( "卖家端查询订单详情  发生异常" );
         map.put ( "msg",e.getMessage () );
         map.put ( "url","/dianCan/seller/order/list" );
         return new ModelAndView ( "common/error",map );
        }
        map.put ( "orderDTO",orderDTO );
        return new ModelAndView ( "order/detail",map );
    }
    //TODO 点击完结的时候，等待支付的不会改变状态
    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
            orderDTO=orderService.findOne ( orderId );
            if (orderDTO != null) {
                orderService.finish ( orderDTO );
            }
        } catch (SellException e) {
            log.error ( "卖家端完结订单 发生异常{}",e );
            map.put ( "msg",e.getMessage () );
            map.put ( "url","/dianCan/seller/order/list" );
            return new ModelAndView ("common/error",map );
        }
        map.put ( "msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage ());
        map.put ( "url","/dianCan/seller/order/list" );
        return new ModelAndView (  "common/success",map);
    }


}
