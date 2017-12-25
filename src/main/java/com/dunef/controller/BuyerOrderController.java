package com.dunef.controller;

import com.dunef.converter.OrderForm2OderDTOConverter;
import com.dunef.dto.OrderDTO;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.form.OrderForm;
import com.dunef.service.BuyerService;
import com.dunef.service.OrderService;
import com.dunef.utils.ResultVOUtil;
import com.dunef.vo.ResultVO;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午6:41 2017/12/21
 * @Modified By:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
            @Autowired
            private OrderService orderService;

            @Autowired
            private BuyerService buyerService;

    private OrderDTO orderDTO;
    private OrderDTO createResult;
    private HashMap<String, String> map;
    private PageRequest request;
    private Page<OrderDTO> orderDTOPage;

    //创建订单
        @RequestMapping("/create")
        public ResultVO<Map<String,String>>create(@Valid OrderForm orderForm, BindingResult bindingResult){
            if (bindingResult.hasErrors ()){

                log.error ( "创建订单 参数不正确,orderForm={}",orderForm );
                throw new SellException ( ResultEnum.PARAM_ERROR.getCode ()
                        ,bindingResult.getFieldError ().getDefaultMessage () );
            }
            orderDTO = OrderForm2OderDTOConverter.convert ( orderForm );
            if (CollectionUtils.isEmpty ( orderDTO.getOrderDetailList () )){
                log.error ( "创建订单 购物车不能为空" );
                throw new SellException ( ResultEnum.CART_EMPTY );
            }
            createResult = orderService.create ( orderDTO );

            //看以下几行
            map = new HashMap<> ();
            map.put ( "orderId",createResult.getOrderId () );


            return ResultVOUtil.success ( map );
        }
        //订单列表\
        @GetMapping("/list")
        public ResultVO<List<OrderDTO>> list(@RequestParam ("openid") String openid,
                                             @RequestParam (value = "page",defaultValue = "0") Integer page,
                                             @RequestParam (value = "size",defaultValue = "10") Integer size){
            if(StringUtils.isEmpty ( openid )){
                log.error ( "查询订单列表 openid为空" );
                throw new SellException ( ResultEnum.PARAM_ERROR );
            }
            request = new PageRequest ( page, size );
            orderDTOPage = orderService.findList ( openid, request );
            return ResultVOUtil.success ( orderDTOPage.getContent () );
           // return ResultVOUtil.sussess ();

        }
        //订单详情
        @GetMapping("/detail")
        public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                         @RequestParam("orderId") String orderId){
//        // 不安全的做法
//            orderDTO=orderService.findOne ( orderId );
            orderDTO= buyerService.findOrderOne ( openid, orderId );
            return ResultVOUtil.sussess (orderDTO);
        }
        //取消订单
        @GetMapping("/cancel")
        public ResultVO cancel(@RequestParam("openid") String openid,
                               @RequestParam("orderId") String orderId){
//            // 不安全的做法，改进cancel
//            orderDTO=orderService.findOne ( orderId );
            buyerService.cancelOrder ( openid, orderId );
            orderService.cancel ( orderDTO );
            return ResultVOUtil.sussess ();

        }

}






















