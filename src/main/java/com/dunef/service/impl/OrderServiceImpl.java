package com.dunef.service.impl;

import com.dunef.converter.OrderMaster20rederDTOConverter;
import com.dunef.dataobject.OrderDetail;
import com.dunef.dataobject.OrderMaster;
import com.dunef.dataobject.ProductInfo;
import com.dunef.dto.CartDTO;
import com.dunef.dto.OrderDTO;
import com.dunef.enums.OrderStatusEnum;
import com.dunef.enums.PayStatusEnum;
import com.dunef.enums.ResultEnum;
import com.dunef.exception.SellException;
import com.dunef.repository.OrderDetailRepository;
import com.dunef.repository.OrderMasterRepository;
import com.dunef.service.OrderService;
import com.dunef.service.ProductService;
import com.dunef.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午8:01 2017/12/20
 * @Modified By:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private ProductInfo productInfo;
    private BigDecimal orderAmount;
    private String orderId;
    private OrderMaster orderMaster;
    private List<CartDTO> cartDTOList;
    private CartDTO cartDTO;
    private List<OrderDetail> orderDetailList;
    private OrderDTO orderDTO;
    private Page<OrderMaster> orderMasterPage;
    private PageImpl<OrderDTO> orderDTOpage;
    private List<OrderDTO> orderDTOList;
    private OrderMaster updateResult;


    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        orderId = KeyUtil.genUniqueKey ();
        orderAmount = new BigDecimal ( BigInteger.ZERO );
//非lamader表达式写法
         cartDTOList = new ArrayList<> ();

        //1、查询商品(数量，价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList ()) {
            productInfo = productService.findOne ( orderDetail.getProductId () );
            if (productInfo == null) {
                throw new SellException ( ResultEnum.PRODUCT_NOT_EXIST );
            }
            //2、计算订单总价
            orderAmount= productInfo.getProductPrice ()
                    .multiply ( new BigDecimal ( orderDetail.getProductQuantity () ) )
                    .add ( orderAmount );
                //订单详情入库
                orderDetail.setDetailId (KeyUtil.genUniqueKey ()  );
                orderDetail.setOrderId (  orderId);
                BeanUtils.copyProperties (productInfo,orderDetail);
                orderDetailRepository.save ( orderDetail );
//非lamader表达式写法
            cartDTO = new CartDTO ( orderDetail.getProductId (), orderDetail.getProductQuantity () );
            cartDTOList.add ( cartDTO );
        }
        //3、写入订单数据库(orderMaster)
        orderMaster = new OrderMaster ();
        BeanUtils.copyProperties ( orderDTO,orderMaster );;
        orderMaster.setOrderId ( orderId );
        orderMaster.setOrderAmount ( orderAmount );
        orderMaster.setOrderStatus ( OrderStatusEnum.NEW.getCode () );
        orderMaster.setPayStatus ( PayStatusEnum.WAIT.getCode () );

        orderMasterRepository.save ( orderMaster );

        //4、扣库存
//        cartDTOList=orderDTO.getOrderDetailList ().stream ().map ( e ->
//        new CartDTO ( e.getProductId (),e.getProductQuantity () )
//        ).collect ( Collectors.toList () );
        productService.decreaseStock ( cartDTOList );
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        orderMaster=  orderMasterRepository.findOne ( orderId );
        if (orderMaster==null){
            throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        }
        orderDetailList = orderDetailRepository.findByOrderId ( orderId );
        if (CollectionUtils.isEmpty (orderDetailList)){
            throw new SellException ( ResultEnum.ORDERDETAIL_NOT_EXIST );
        }
        orderDTO = new OrderDTO ();
        BeanUtils.copyProperties ( orderMaster,orderDTO );
        orderDTO.setOrderDetailList ( orderDetailList );
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        orderMasterPage = orderMasterRepository.findByBuyerOpenid ( buyerOpenid, pageable );

        orderDTOList = OrderMaster20rederDTOConverter.convert ( orderMasterPage.getContent () );

        orderDTOpage = new PageImpl<OrderDTO> (orderDTOList,pageable,orderMasterPage.getTotalElements ());

        return orderDTOpage;
    }

    //取消订单
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
     orderMaster=new  OrderMaster ();
    //判断订单状态
    if (!orderDTO.getOrderStatus ().equals ( OrderStatusEnum.NEW.getCode () )){
        log.error ( "取消订单 订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId (),orderDTO.getOrderStatus () );
        throw  new SellException ( ResultEnum.ORDER_STATUS_ERROR );
    }
    //修改订单状态
        orderDTO.setOrderStatus ( OrderStatusEnum.CANCEL.getCode () );
        BeanUtils.copyProperties ( orderDTO,orderMaster );
        updateResult = orderMasterRepository.save ( orderMaster );
        if (updateResult==null){
            log.error ( "取消订单更新失败，orderMaster={}", orderMaster );
            throw new SellException ( ResultEnum.ORDER_UPDATE_FAIL );
        }
        //返回库存
        if (CollectionUtils.isEmpty ( orderDTO.getOrderDetailList () )){
            log.error ( "取消订单 订单中无商品详情，orderDTO={}",orderDTO );
            throw new SellException ( ResultEnum.ORDER_DETAIL_EMPTY );
        }
        cartDTOList= orderDTO.getOrderDetailList ().stream ()
                .map ( e -> new CartDTO ( e.getProductId (),e.getProductQuantity () ))
                .collect ( Collectors.toList () );
        productService.increaseStock (  cartDTOList);
     //如果已支付，需要退款
        if (orderDTO.getPayStatus ().equals ( PayStatusEnum.SUCCESS.getCode () )){
            //TODO
        }
        return orderDTO;
    }
    //完结订单
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus ().equals ( OrderStatusEnum.NEW.getCode () )){
            log.error ( "完结订单 订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId (),orderDTO.getOrderStatus ()  );
            throw new SellException ( ResultEnum.ORDER_STATUS_ERROR );
        }
        //修改订单状态
        orderDTO.setOrderStatus ( OrderStatusEnum.FINISHED.getCode () );
        orderMaster=new OrderMaster ();
        BeanUtils.copyProperties ( orderDTO,orderMaster );
        updateResult= orderMasterRepository.save ( orderMaster );
        if (updateResult==null){
            log.error ( "完结订单更新失败，orderMaster={}", orderMaster );
            throw new SellException ( ResultEnum.ORDER_UPDATE_FAIL );
        }
        return orderDTO;
    }
    //支付订单
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (orderDTO.getOrderStatus ().equals (OrderStatusEnum.NEW.getCode ())) {
            log.error ( "订单支付成功 支付订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId (),orderDTO.getOrderStatus () );
            throw new SellException ( ResultEnum.ORDER_STATUS_ERROR );
        }
        //判断支付状态
        if (!orderDTO.getPayStatus ().equals (PayStatusEnum.WAIT.getCode ())){
            log.error ( "订单支付完成 订单支付状态不正确，orderDTO={}",orderDTO );
            throw new SellException ( ResultEnum.ORDER_PAY_STATUS_ERROR );
        }
        //修改订单支付状态
        orderDTO.setPayStatus ( PayStatusEnum.SUCCESS.getCode () );
        orderMaster=new OrderMaster ();
        BeanUtils.copyProperties ( orderDTO,orderMaster );
        updateResult=orderMasterRepository.save ( orderMaster );
        if (updateResult==null){
            log.error ( "订单支付完成 更新失败, orderMaster={}",orderMaster );
            throw new SellException ( ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
