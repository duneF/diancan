package com.dunef.controller;

import com.dunef.dto.OrderDTO;
import com.dunef.service.OrderService;
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
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
        @Autowired
        private OrderService orderService;
    private PageRequest pageRequest;
    private Page<OrderDTO> orderDTOPage;

    /**
     *
     * @param page 第几页，从1页开始
     * @param size 一页有多少数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page" ,defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        pageRequest = new PageRequest ( page-1, size );
        orderDTOPage = orderService.findList ( pageRequest );
        map.put ( "orderDTOPage",orderDTOPage );
        return new ModelAndView ( "order/list",map );
    }


}
