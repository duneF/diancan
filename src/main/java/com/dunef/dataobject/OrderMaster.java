package com.dunef.dataobject;

import com.dunef.enums.OrderStatusEnum;
import com.dunef.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午3:44 2017/12/19
 * @Modified By:
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /*订单id*/
    @Id
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
    private BigDecimal  orderAmount;
    /*订单状态,默认为新下单*/
    private Integer  orderStatus= OrderStatusEnum.NEW.getCode ();
    /*支付状态 默认为0未支付*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode ();
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;

    //@Transient 注解可以在查询数据库时候，若是没有忽略掉
   //但是我们通常把这种在数据库不存在的属性，封装到DTO里面
//    @Transient
//    private List<OrderDetail> orderDetailList;
}
