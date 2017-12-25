package com.dunef.dto;

import com.dunef.dataobject.OrderDetail;
import com.dunef.enums.OrderStatusEnum;
import com.dunef.enums.PayStatusEnum;
import com.dunef.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午6:48 2017/12/19
 * @Modified By:
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderDTO {

    /*订单id*/
    private String  orderId;
    /*买家名字*/
    private String  buyerName;
    /*买家手机号*/
    private String  buyerPhone;
    /*买家地址*/
    private String  buyerAddress;
    /*买家微信OpenId*/
    private String  buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态,默认为新下单*/
    private Integer  orderStatus;
    /*支付状态 默认为0未支付*/
    private Integer payStatus;
    /*创建时间*/ //TODO
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /*更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
